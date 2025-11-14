package com.codeplace.postsandroidapp.feature_settings.domain.di

import android.content.Context
import androidx.compose.runtime.Stable
import com.codeplace.postsandroidapp.PostsAndroidApp
import com.codeplace.postsandroidapp.feature_settings.domain.SettingsRepository
import com.codeplace.postsandroidapp.feature_settings.domain.use_case.GetAppThemeUseCase
import com.codeplace.postsandroidapp.feature_settings.domain.use_case.SaveAppThemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SettingsDomainModule {

    @Stable
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): PostsAndroidApp {
        return app as PostsAndroidApp
    }


    @Provides
    @Singleton
    fun provideGetAppThemeUseCase(settingsRepository: SettingsRepository): GetAppThemeUseCase{
        return GetAppThemeUseCase(
            settingsRepository = settingsRepository
        )
    }

    @Provides
    @Singleton
    fun provideSaveAppThemeUseCase(settingsRepository: SettingsRepository): SaveAppThemeUseCase{
        return SaveAppThemeUseCase(
            settingsRepository = settingsRepository
        )
    }
}