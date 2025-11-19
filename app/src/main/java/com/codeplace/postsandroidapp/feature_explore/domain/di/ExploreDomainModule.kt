package com.codeplace.postsandroidapp.feature_explore.domain.di

import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreLocalDataSource
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreRemoteDataSource
import com.codeplace.postsandroidapp.feature_explore.data.repository.PostsRepositoryImpl
import com.codeplace.postsandroidapp.feature_explore.domain.repository.PostsRepository
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetCommentsUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object ExploreDomainModule {



    @Provides
    @Singleton
    fun providePostUseCase(repository: PostsRepository): GetPostUseCase{
        return GetPostUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePostsUseCase(repository: PostsRepository): GetPostsUseCase{
        return GetPostsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCommentsUseCases(repository: PostsRepository): GetCommentsUseCase{
        return GetCommentsUseCase(repository)
    }


}