package com.codeplace.postsandroidapp.feature_explore.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreLocalDataSource
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreLocalDataSourceImpl
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreRemoteDataSource
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreRemoteDataSourceImpl
import com.codeplace.postsandroidapp.feature_explore.data.local.entity.SearchHistoryEntity
import com.codeplace.postsandroidapp.feature_explore.data.local.SearchHistorySerializer
import com.codeplace.postsandroidapp.feature_explore.data.repository.ExploreRepositoryImpl
import com.codeplace.postsandroidapp.feature_explore.domain.repository.ExploreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    ): ExploreRemoteDataSource {
        return ExploreRemoteDataSourceImpl(httpClient = httpClient)
    }

    val Context.dataStore: DataStore<SearchHistoryEntity> by dataStore(
        fileName = "ssearch_history.json",
        serializer = SearchHistorySerializer,
    )
    @Provides
    @Singleton
    fun provideProtoDataStore(
        @ApplicationContext context: Context): DataStore<SearchHistoryEntity>{
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dataStore: DataStore<SearchHistoryEntity>): ExploreLocalDataSource {
        return ExploreLocalDataSourceImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideExploreRepository(
        exploreRemoteDataSource: ExploreRemoteDataSource,
        exploreLocalDataSource: ExploreLocalDataSource
    ): ExploreRepository {
        return ExploreRepositoryImpl(
            exploreRemoteDataSource = exploreRemoteDataSource,
            exploreLocalDataSource = exploreLocalDataSource
        )
    }



}