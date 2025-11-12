package com.codeplace.postsandroidapp.feature_explore.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.postsandroidapp.core.domain.onError
import com.codeplace.postsandroidapp.core.domain.onSuccess
import com.codeplace.postsandroidapp.feature_explore.domain.model.Post
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExplorePostsViewModel @Inject constructor(
    val getPostsUseCase: GetPostsUseCase
): ViewModel() {


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    private val _posts = MutableStateFlow(emptyList<Post>())
    val posts:StateFlow<List<Post>> = _posts.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage:StateFlow<String> = _errorMessage.asStateFlow()


    init {
        getPosts()
    }

    fun getPosts() = viewModelScope.launch {
        _isLoading.value = true
        getPostsUseCase()
            .onSuccess { posts ->
                _posts.value = posts
                _errorMessage.value = ""
                _isLoading.value = false
            }
            .onError { error ->
                _errorMessage.value = error.name
                _isLoading.value = false
            }

    }

}