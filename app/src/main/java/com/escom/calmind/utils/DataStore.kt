package com.escom.calmind.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_SETTINGS)

@Composable
fun UpdateIsFirstTime() {
    val context = LocalContext.current
    LaunchedEffect(true) {
        context.dataStore.edit { settings ->
            val key = booleanPreferencesKey(IS_FIRST_TIME)
            settings[key] = false
        }
    }
}
