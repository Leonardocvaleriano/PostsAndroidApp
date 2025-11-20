package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import com.codeplace.postsandroidapp.feature_explore.domain.repository.ExploreRepository
import jakarta.inject.Inject

class GetRecentWordSearchesUseCase @Inject constructor(
    private val repository: ExploreRepository
) {
    suspend operator fun invoke(): Result<SearchHistory, DataError.Local> {
        return repository.getRecentPostSearches()
    }
}