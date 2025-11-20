package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import com.codeplace.postsandroidapp.feature_explore.domain.repository.ExploreRepository
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    val postRepository: ExploreRepository
) {
    suspend operator fun invoke(postId: Int) = postRepository.getComments(postId = postId)
}