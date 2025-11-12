package com.codeplace.postsandroidapp.feature_settings.domain.di

import com.codeplace.postsandroidapp.feature_settings.domain.SettingsRepository
import com.codeplace.postsandroidapp.feature_settings.domain.use_case.GetAppThemeUseCase
import com.codeplace.postsandroidapp.feature_settings.domain.use_case.SaveAppThemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SettingsDomainModule {

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