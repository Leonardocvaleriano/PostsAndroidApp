package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import com.codeplace.postsandroidapp.feature_explore.domain.repository.PostsRepository
import jakarta.inject.Inject
import kotlinx.collections.immutable.PersistentList

class GetRecentWordSearchesUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(): Result<SearchHistory, DataError.Local> {
        return repository.getRecentPostSearches()
    }
}