package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.repository.ExploreRepository
import javax.inject.Inject

class SaveFavouritePostUseCase @Inject constructor(
    private val exploreRepository: ExploreRepository
) {

    suspend operator fun invoke(post: Post): Result<Unit, DataError.Local> = exploreRepository.saveFavouritePost(post = post)

}