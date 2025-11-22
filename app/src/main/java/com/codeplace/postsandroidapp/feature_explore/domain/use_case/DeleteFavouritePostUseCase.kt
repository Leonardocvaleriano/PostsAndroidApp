package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.repository.ExploreRepository
import javax.inject.Inject

class DeleteFavouritePostUseCase @Inject constructor(
    val repository: ExploreRepository
) {
    suspend operator fun invoke(post: Post): Result<Unit, DataError.Local> {
        return repository.deleteFavouritePost(post = post)
    }
}