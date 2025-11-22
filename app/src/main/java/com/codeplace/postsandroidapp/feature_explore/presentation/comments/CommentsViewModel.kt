package com.codeplace.postsandroidapp.feature_explore.presentation.comments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.models.Comment
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetCommentsUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    val getPostUseCase: GetPostUseCase,
    val getCommentsUseCase: GetCommentsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _commentsUiState = MutableStateFlow<List<Comment>?>(emptyList())

    val commentsUiState: StateFlow<List<Comment>?> = _commentsUiState.asStateFlow()

    private val _postEntity = MutableStateFlow<Post?>(null)
    val postEntity: StateFlow<Post?> = _postEntity.asStateFlow()

    private val _errorPost = MutableStateFlow<String>("")
    val errorPost: StateFlow<String> = _errorPost.asStateFlow()


    private val _errorComments = MutableStateFlow<String>("")
    val errorComments: StateFlow<String> = _errorComments.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    init {
        savedStateHandle.get<Int>("postId")?.let { postId ->
            loadPostAndComments(postId = postId)
        }

    }


    fun loadPostAndComments(postId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val postResult = async { getPostUseCase(postId = postId) }
            val commentsResult = async { getCommentsUseCase(postId = postId) }
            val (post, comments) = awaitAll(postResult, commentsResult)

            when (post) {
                is Result.Success -> {
                    _postEntity.value = post.data as Post
                }
                is Result.Error -> {
                    _errorComments.value = post.error.name
                }
            }

            when (comments) {
                is Result.Success -> {
                    _commentsUiState.value = comments.data as List<Comment>
                }

                is Result.Error -> {
                    _errorComments.value = comments.error.name
                }
            }
            _isLoading.value = false

        }
    }

}
