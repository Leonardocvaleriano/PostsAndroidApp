package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import com.codeplace.postsandroidapp.core.domain.NetworkError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.model.Post
import com.codeplace.postsandroidapp.feature_explore.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    val repository: PostsRepository
){
    suspend operator fun invoke(): Result<List<Post>, NetworkError> {
        return repository.getPosts()
    }
}