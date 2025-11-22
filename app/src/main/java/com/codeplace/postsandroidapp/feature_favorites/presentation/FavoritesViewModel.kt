package com.codeplace.postsandroidapp.feature_favorites.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.postsandroidapp.core.domain.onError
import com.codeplace.postsandroidapp.core.domain.onSuccess
import com.codeplace.postsandroidapp.core.presentation.util.UiText
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetSavedPostUseCase
import com.codeplace.postsandroidapp.core.presentation.screens.toUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    val getSavedPostUseCase: GetSavedPostUseCase
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

    private fun loadSavedPosts() = viewModelScope.launch(Dispatchers.IO) {
        _isLoading.value = true
        getSavedPostUseCase.invoke()
            .onSuccess { savedPosts ->
                Log.d("FavoritesViewModel", "Success, savedPosts: ${savedPosts}")

                _favoritePosts.value = savedPosts
                _isLoading.value = false
            }
            .onError { errorMessage ->
                Log.d("FavoritesViewModel", "Error, savedPosts: ${errorMessage}")

                _error.value = errorMessage.toUiText()
                _isLoading.value = false

            }
    }

}