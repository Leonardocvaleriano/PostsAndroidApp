package com.codeplace.postsandroidapp.feature_explore.presentation.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.postsandroidapp.core.domain.onError
import com.codeplace.postsandroidapp.core.domain.onSuccess
import com.codeplace.postsandroidapp.core.presentation.util.UiText
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetSavedPostUseCase
import com.codeplace.postsandroidapp.core.presentation.screens.toUiText
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.DeleteFavouritePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    val getSavedPostUseCase: GetSavedPostUseCase,
    val deleteFavouritePostUseCase: DeleteFavouritePostUseCase
) : ViewModel() {


    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _favoritePosts = MutableStateFlow<List<Post>>(emptyList())
    val favoritePosts = _favoritePosts.asStateFlow()

    private val _error = MutableStateFlow<UiText?>(null)
    val error = _error.asStateFlow()


    init {
        loadSavedPosts()
    }

    fun deleteFavouritePost(post: Post) {
        _isLoading.value = true
        viewModelScope.launch {
            deleteFavouritePostUseCase.invoke(post)
                .onSuccess {
                    _isLoading.value = false
                }
                .onError {
                    _isLoading.value = false
                }
        }


    }

    private fun loadSavedPosts() = viewModelScope.launch {
        _isLoading.value = true
        getSavedPostUseCase.invoke()
            .onSuccess { savedPosts ->
                savedPosts.collect { savedPosts ->
                    val savedPostsWithFav = savedPosts.map { post ->
                        post.copy(isFavourite =  true)
                    }
                    _favoritePosts.value = savedPostsWithFav
                }
                _isLoading.value = false
            }
            .onError { errorMessage ->
                Log.d("FavoritesViewModel", "Error, savedPosts: ${errorMessage}")

                _error.value = errorMessage.toUiText()
                _isLoading.value = false

            }
    }

}