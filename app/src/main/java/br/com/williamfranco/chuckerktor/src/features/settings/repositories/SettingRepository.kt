package br.com.williamfranco.chuckerktor.src.features.settings.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import br.com.williamfranco.chuckerktor.src.common.constants.ValueConstant
import br.com.williamfranco.chuckerktor.src.features.settings.models.SettingModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "chucker_ktor_settings",
)

interface SettingRepository {
    val theme: Flow<SettingModel>
    suspend fun readTheme(): SettingModel
    suspend fun updateTheme(isDarkTheme: Boolean)
}

class SettingRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : SettingRepository {

    override val theme: Flow<SettingModel> = dataStore.data.map { preferences ->
        SettingModel(
            isDarkTheme = preferences[DARK_MODE_KEY] ?: false,
        )
    }

    override suspend fun readTheme(): SettingModel = theme.first()

    override suspend fun updateTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isDarkTheme
        }
    }

    private companion object {
        val DARK_MODE_KEY = booleanPreferencesKey(ValueConstant.DARK_MODE)
    }
}
