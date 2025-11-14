package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import com.codeplace.postsandroidapp.feature_explore.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostByPostIdUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {

    suspend operator fun invoke(postId: Int) = postsRepository.getPost(postId = postId)

}