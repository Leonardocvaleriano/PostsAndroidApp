package com.codeplace.postsandroidapp.feature_explore.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.postsandroidapp.core.domain.onError
import com.codeplace.postsandroidapp.core.domain.onSuccess
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    val getPostsUseCase: GetPostsUseCase
): ViewModel() {


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    private val _posts = MutableStateFlow(emptyList<Post>())
    val posts:StateFlow<List<Post>> = _posts.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage:StateFlow<String> = _errorMessage.asStateFlow()

    private var fullPostList: List<Post> = emptyList()


    fun onSearch(query: String){
        val filteredPosts = if (query.isBlank()){
            fullPostList
        } else {
            fullPostList.filter { post ->
                post.title.contains(query, ignoreCase = true) ||
                        post.body.contains(query, ignoreCase = true)
            }
        }
        _posts.value = filteredPosts
    }

    init {
        loadPosts()
    }

    fun loadPosts() = viewModelScope.launch {
        _isLoading.value = true
        getPostsUseCase()
            .onSuccess { posts ->
                fullPostList = posts
                _posts.value = posts
                _isLoading.value = false
            }
            .onError { error ->
                _errorMessage.value = error.name
                _isLoading.value = false
            }

    }

}