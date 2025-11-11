package com.codeplace.postsandroidapp.di

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Stable
import com.codeplace.postsandroidapp.PostsAndroidApp
import com.codeplace.postsandroidapp.feature_explore.data.repository.PostsRepositoryImpl
import com.codeplace.postsandroidapp.feature_explore.domain.repository.PostsRepository
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetCommentsByPostIdUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostByPostIdUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostsUseCase
import com.codeplace.postsandroidapp.feature_explore.domain.use_case.GetPostUseCases

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Stable
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): PostsAndroidApp {
        return app as PostsAndroidApp
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("Http call:", message)
                    }
                }
                level = LogLevel.BODY
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }

    }

    @Provides
    @Singleton
    fun provideExploreRepository(
        api: HttpClient,
    ): PostsRepository {
        return PostsRepositoryImpl(
            api = api
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