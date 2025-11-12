package com.codeplace.postsandroidapp.feature_settings.data.local.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.codeplace.postsandroidapp.feature_settings.data.local.datasources.LocalPrefDataSource
import com.codeplace.postsandroidapp.feature_settings.data.local.datasources.LocalPrefDataSourceImpl
import com.codeplace.postsandroidapp.feature_settings.data.local.datasources.SettingsRepositoryImpl
import com.codeplace.postsandroidapp.feature_settings.domain.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FeaturesSettingsDataModule {

    @Provides
    @Singleton
    fun provideLocalPrefDataSource(dataStore: DataStore<Preferences>): LocalPrefDataSource{
        return LocalPrefDataSourceImpl(dataStore)
    }


    @Provides
    @Singleton
    fun provideSettingsRepository(localPrefDataSource: LocalPrefDataSource): SettingsRepository{
        return SettingsRepositoryImpl(localPrefDataSource)
    }

}
