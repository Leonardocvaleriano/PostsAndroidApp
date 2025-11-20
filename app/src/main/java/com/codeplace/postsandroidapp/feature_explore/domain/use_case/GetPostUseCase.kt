package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import com.codeplace.postsandroidapp.feature_explore.domain.repository.ExploreRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val exploreRepository: ExploreRepository
) {

    suspend operator fun invoke(postId: Int) = exploreRepository.getPost(postId = postId)

}