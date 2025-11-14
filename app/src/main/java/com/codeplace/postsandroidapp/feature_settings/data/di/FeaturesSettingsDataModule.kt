package com.codeplace.postsandroidapp.feature_settings.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.codeplace.postsandroidapp.feature_settings.data.datasources.LocalDataSource
import com.codeplace.postsandroidapp.feature_settings.data.datasources.LocalDataSourceImpl
import com.codeplace.postsandroidapp.feature_settings.data.SettingsRepositoryImpl
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
    fun provideLocalPrefDataSource(dataStore: DataStore<Preferences>): LocalDataSource{
        return LocalDataSourceImpl(dataStore)
    }


    @Provides
    @Singleton
    fun provideSettingsRepository(localDataSource: LocalDataSource): SettingsRepository{
        return SettingsRepositoryImpl(localDataSource)
    }

}
