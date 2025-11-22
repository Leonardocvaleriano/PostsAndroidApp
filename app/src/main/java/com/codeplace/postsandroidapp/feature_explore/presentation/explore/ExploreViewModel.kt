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
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.SavePostUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.SaveRecentPostSearchesUseCase
import com.codeplace.postsandroidapp.core.presentation.screens.toUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    val getPostsUseCase: GetPostsUseCase,
    val saveRecentSearch: SaveRecentPostSearchesUseCase,
    val getSavedSearchHistory: GetRecentWordSearchesUseCase,
    val savePostUseCase: SavePostUseCase
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


    private val _posts = MutableStateFlow(emptyList<Post>())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _errorMessage = MutableStateFlow<UiText>(UiText.DynamicString(""))
    val errorMessage: StateFlow<UiText> = _errorMessage.asStateFlow()

    private var fullPostEntityList: List<Post> = emptyList()

    private val _recentSearches = MutableStateFlow<List<String>>(emptyList())
    val recentSearches = _recentSearches.asStateFlow()


    fun savePost(post: Post) = viewModelScope.launch(Dispatchers.IO) {

        savePostUseCase.invoke(post)
            .onSuccess {
                _uiEvent.emit(ExploreUiEvent.ShowSnackBar(messsage = UiText.StringResourceId(R.string.post_successfully_saved)))
            }
            .onError { errorMessage ->
                _uiEvent.emit(ExploreUiEvent.ShowSnackBar(messsage = errorMessage.toUiText()))
            }
    }

    fun onSearch(query: String) {

        if (query.isBlank() || query.isEmpty()) {
            loadPosts()
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

    init {
        loadPosts()
    }


    fun loadRecentPostSearches() = viewModelScope.launch(Dispatchers.IO) {
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
        val filteredPosts = fullPostEntityList.filter { post ->
            post.title.contains(query, ignoreCase = true) ||
                    post.body.contains(query, ignoreCase = true)
        }
        _posts.value = filteredPosts

    }

    fun loadPosts() = viewModelScope.launch(Dispatchers.IO) {
        _isLoading.value = true
        getPostsUseCase()
            .onSuccess { posts ->
                fullPostEntityList = posts
                _posts.value = posts
                _isLoading.value = false
            }
            .onError { error ->
                _errorMessage.value = error.toUiText()
                _isLoading.value = false
            }

    }

}