package com.codeplace.postsandroidapp.feature_explore.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.codeplace.postsandroidapp.core.data.remote.AppDatabase
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreLocalDataSource
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreLocalDataSourceImpl
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreRemoteDataSource
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreRemoteDataSourceImpl
import com.codeplace.postsandroidapp.feature_explore.data.local.datastore.entity.SearchHistoryEntity
import com.codeplace.postsandroidapp.feature_explore.data.local.datastore.SearchHistorySerializer
import com.codeplace.postsandroidapp.feature_explore.data.local.room.PostDao
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
    fun provideExploreRemoteDataSource(
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
    fun provideExploreProtoDataStore(
        @ApplicationContext context: Context
    ): DataStore<SearchHistoryEntity> {
        return context.dataStore
    }


    @Provides
    @Singleton
    fun providePostDao(
        db: AppDatabase
    ): PostDao {
        return db.postDao()
    }

    @Provides
    @Singleton
    fun provideExploreLocalDataSource(
        dataStore: DataStore<SearchHistoryEntity>,
        postDao: PostDao
    ): ExploreLocalDataSource {
        return ExploreLocalDataSourceImpl(
            dataStore = dataStore,
            postDao = postDao
        )
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