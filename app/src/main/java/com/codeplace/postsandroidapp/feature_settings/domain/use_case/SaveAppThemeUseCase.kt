package com.codeplace.postsandroidapp.feature_settings.domain.use_case

import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.SettingsRepository
import javax.inject.Inject


class SaveAppThemeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(appTheme: AppTheme): Unit {
        return settingsRepository.saveAppTheme(appTheme = appTheme)
    }
}