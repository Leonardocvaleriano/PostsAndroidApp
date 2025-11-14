package com.codeplace.postsandroidapp.feature_explore.domain.di

import com.codeplace.postsandroidapp.feature_explore.data.datasources.RemoteDataSource
import com.codeplace.postsandroidapp.feature_explore.data.repository.PostsRepositoryImpl
import com.codeplace.postsandroidapp.feature_explore.domain.repository.PostsRepository
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetCommentsByPostIdUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostByPostIdUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostUseCases
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object ExploreDomainModule {
    @Provides
    @Singleton
    fun provideExploreRepository(
        remoteDataSource: RemoteDataSource
    ): PostsRepository {
        return PostsRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }

    @Provides
    @Singleton
    fun providePostUseCases(repository: PostsRepository): GetPostUseCases {
        return GetPostUseCases(
            getPostsUseCase = GetPostsUseCase(repository),
            getPostByPostIdUseCase = GetPostByPostIdUseCase(repository),
            getCommentsByPostIdUseCase = GetCommentsByPostIdUseCase(repository)
        )
    }
}