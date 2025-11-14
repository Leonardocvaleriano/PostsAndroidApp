package com.codeplace.postsandroidapp.feature_explore.data.di

import com.codeplace.postsandroidapp.feature_explore.data.datasources.RemoteDataSource
import com.codeplace.postsandroidapp.feature_explore.data.datasources.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ExploreDataModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        httpClient: HttpClient
    ): RemoteDataSource {
        return RemoteDataSourceImpl(httpClient = httpClient)
    }
}