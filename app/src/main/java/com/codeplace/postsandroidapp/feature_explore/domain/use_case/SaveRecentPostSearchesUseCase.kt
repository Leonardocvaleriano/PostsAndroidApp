package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import com.codeplace.postsandroidapp.feature_explore.domain.repository.ExploreRepository
import jakarta.inject.Inject

class SaveRecentPostSearchesUseCase @Inject constructor(
    private val repository: ExploreRepository
) {
    suspend operator fun invoke(searchHistory: SearchHistory): Unit {

        return repository.saveRecentPostSearches(searchHistory)
    }
}