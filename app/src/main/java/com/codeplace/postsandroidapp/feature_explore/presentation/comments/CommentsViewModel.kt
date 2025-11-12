package com.codeplace.postsandroidapp.feature_explore.presentation.comments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.model.Comment
import com.codeplace.postsandroidapp.feature_explore.domain.model.Post
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetCommentsByPostIdUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostByPostIdUseCase
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
    val getPostByPostIdUseCase: GetPostByPostIdUseCase,
    val getCommentsByPostIdUseCase: GetCommentsByPostIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

//    private val _commentsUiState = MutableStateFlow(CommentPostUiState())
//    val commentUiState: StateFlow<CommentPostUiState> = _commentsUiState.asStateFlow()
//

    private val _comments = MutableStateFlow<List<Comment>?>(emptyList())

    val comments: StateFlow<List<Comment>?> = _comments.asStateFlow()

    private val _post = MutableStateFlow<Post?>(null)
    val post: StateFlow<Post?> = _post.asStateFlow()

    private val _errorPost = MutableStateFlow<String>("")
    val errorPost: StateFlow<String> = _errorPost.asStateFlow()


    private val _errorComments = MutableStateFlow<String>("")
    val errorComments: StateFlow<String> = _errorComments.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    init {
        savedStateHandle.get<Int>("postId")?.let { postId ->
            getPostAndCommentsByPostId(postId = postId)
        }

    }


    fun getPostAndCommentsByPostId(postId: Int) {

        viewModelScope.launch {
            _isLoading.value = true
            val postResult = async { getPostByPostIdUseCase(postId = postId) }
            val commentsResult = async { getCommentsByPostIdUseCase(postId = postId) }
            val (post, comments) = awaitAll(postResult, commentsResult)

            when (post) {
                is Result.Success -> {
                    _post.value = post.data as Post
                }
                is Result.Error -> {
                    _errorComments.value = post.error.name
                }
            }

            when (comments) {
                is Result.Success -> {
                    _comments.value = comments.data as List<Comment>
                }

                is Result.Error -> {
                    _errorComments.value = comments.error.name
                }
            }
            _isLoading.value = false

        }
    }

}
