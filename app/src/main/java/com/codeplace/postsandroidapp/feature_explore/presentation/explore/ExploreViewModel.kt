package com.codeplace.postsandroidapp.feature_explore.presentation.explore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.domain.onError
import com.codeplace.postsandroidapp.core.domain.onSuccess
import com.codeplace.postsandroidapp.core.presentation.util.UiText
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostsUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetRecentWordSearchesUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.SaveFavouritePostUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.SaveRecentPostSearchesUseCase
import com.codeplace.postsandroidapp.core.presentation.screens.toUiText
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.DeleteFavouritePostUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetSavedPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    val getPostsUseCase: GetPostsUseCase,
    val saveRecentSearch: SaveRecentPostSearchesUseCase,
    val getSavedSearchHistory: GetRecentWordSearchesUseCase,
    val saveFavouritePostUseCase: SaveFavouritePostUseCase,
    val getSavedPostUseCase: GetSavedPostUseCase,
    val deleteFavouritePostUseCase: DeleteFavouritePostUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<ExploreUiEvent>()
    val uiEvent = _uiEvent

    companion object {
        private const val MAX_RECENT_SEARCHES = 5
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val _isSearchContentLoading = MutableStateFlow(false)
    val isSearchContentLoading: StateFlow<Boolean> = _isSearchContentLoading.asStateFlow()


    private val _allPosts = MutableStateFlow(emptyList<Post>())
    val allPosts: StateFlow<List<Post>> = _allPosts.asStateFlow()

    private var _savedPosts = MutableStateFlow<List<Post>>(emptyList<Post>())

    private val _errorMessage = MutableStateFlow<UiText>(UiText.DynamicString(""))
    val errorMessage: StateFlow<UiText> = _errorMessage.asStateFlow()


    private val _recentSearches = MutableStateFlow<List<String>>(emptyList())
    val recentSearches = _recentSearches.asStateFlow()

    init {
        loadAllPosts()

    }

    fun updateFavouritePostState(post: Post) = viewModelScope.launch {

        getSavedPosts()
        val savedPosts = _savedPosts.first()
        val isPostSaved = savedPosts.any { it.id == post.id }

        if (isPostSaved) {
            deleteFavouritePostUseCase(post)
                .onSuccess {
                    _allPosts.value = _allPosts.value.map { current ->
                        if (current.id == post.id) current.copy(isFavourite = false)
                        else current
                    }
                }
                .onError { error ->
                    _uiEvent.emit(ExploreUiEvent.ShowSnackBar(messsage = error.toUiText()))
                }
        } else {
            saveFavouritePostUseCase(post)
                .onSuccess {
                    _allPosts.value = _allPosts.value.map { current ->
                        if (current.id == post.id) current.copy(isFavourite = true)
                        else current
                    }
                }
                .onError { error ->
                    _uiEvent.emit(ExploreUiEvent.ShowSnackBar(messsage = error.toUiText()))
                }
        }
    }


    private fun getSavedPosts()  = viewModelScope.launch {
        getSavedPostUseCase.invoke()
            .onSuccess { flow ->
                flow.collect { savedPosts ->
                    _savedPosts.value = savedPosts
                }
            }
            .onError { }
    }

    fun onSearch(query: String) {

        if (query.isBlank() || query.isEmpty()) {
            loadAllPosts()

        }
        saveQueryToHistory(query = query)
        filterPosts(query = query)
    }

    private fun saveQueryToHistory(query: String) {
        if (query.isEmpty() || query.isBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            val current = _recentSearches.value.toMutableList()
            current.remove(query)
            current.add(0, query)
            if (current.size > MAX_RECENT_SEARCHES) {
                current.removeLastOrNull()
            }
            _recentSearches.value = current
            saveRecentSearch(
                searchHistory = SearchHistory(recentPostSearches = current)
            )

        }

    }


    fun loadRecentPostSearches() = viewModelScope.launch {
        _isSearchContentLoading.value = true
        getSavedSearchHistory.invoke()
            .onSuccess { history ->
                _recentSearches.value = history.recentPostSearches
                _isSearchContentLoading.value = false
            }
            .onError { error ->
                _isSearchContentLoading.value = false
            }
    }


    private fun filterPosts(query: String) {
        val filteredPosts = allPosts.value.filter { post ->
            post.title.contains(query, ignoreCase = true) ||
                    post.body.contains(query, ignoreCase = true)
        }
        _allPosts.value = filteredPosts

    }

    fun loadAllPosts() = viewModelScope.launch {

        _isLoading.value = true
        val postsResultDeferred = async { getPostsUseCase() }
        val savedPostResultDeferred = async { getSavedPostUseCase() }

        val postResult = postsResultDeferred.await()
        val savedPostResult = savedPostResultDeferred.await()

        postResult.onSuccess { postsList ->
            savedPostResult.onSuccess { savedPostsFlow ->
                savedPostsFlow.collect { savedPosts ->
                    val savedIds = savedPosts.map { it.id }.toSet()

                    val allPostsMapped = postsList.map { post ->
                        post.copy(isFavourite = post.id in savedIds)
                    }

                    _allPosts.value = allPostsMapped
                    _isLoading.value = false

                }
            }
                .onError { errorMessage ->
                    _errorMessage.value = errorMessage.toUiText()
                    _isLoading.value = false
                }




        }
    }
}