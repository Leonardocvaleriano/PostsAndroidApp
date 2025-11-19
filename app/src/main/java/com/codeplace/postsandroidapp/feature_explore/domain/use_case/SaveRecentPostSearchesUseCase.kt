package com.codeplace.postsandroidapp.feature_explore.domain.use_case

import android.util.Log
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import com.codeplace.postsandroidapp.feature_explore.domain.repository.PostsRepository
import jakarta.inject.Inject

class SaveRecentPostSearchesUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(searchHistory: SearchHistory): Unit {

        return repository.saveRecentPostSearches(searchHistory)
    }
}