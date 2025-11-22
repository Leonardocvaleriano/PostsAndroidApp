package com.codeplace.postsandroidapp.feature_explore.domain.di

import com.codeplace.postsandroidapp.feature_explore.domain.repository.ExploreRepository
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.DeleteFavouritePostUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetCommentsUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostsUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetSavedPostUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.SaveFavouritePostUseCase
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
    fun providePostUseCase(repository: ExploreRepository): GetPostUseCase{
        return GetPostUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePostsUseCase(repository: ExploreRepository): GetPostsUseCase{
        return GetPostsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCommentsUseCases(repository: ExploreRepository): GetCommentsUseCase{
        return GetCommentsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSavedPostUseCase(repository: ExploreRepository): GetSavedPostUseCase{
        return GetSavedPostUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSavePostUseCase(repository: ExploreRepository): SaveFavouritePostUseCase{
        return SaveFavouritePostUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideDeleteFavouritePostUseCase(repository: ExploreRepository): DeleteFavouritePostUseCase{
        return DeleteFavouritePostUseCase(repository)
    }


}