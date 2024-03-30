package dicoding.bangkit.submission_fragmtny.ui.theme

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class settingpreference private constructor(private val dataStore: DataStore<Preferences>){

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): kotlinx.coroutines.flow.Flow<Boolean>  {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object{
        @Volatile
        private var INSTANCE : settingpreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): settingpreference{
            return INSTANCE ?: synchronized(this){
                val instance = settingpreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}