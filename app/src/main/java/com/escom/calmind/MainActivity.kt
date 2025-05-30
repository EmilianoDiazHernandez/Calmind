package com.escom.calmind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.escom.calmind.ui.screen.LoginScreen
import com.escom.calmind.ui.screen.SplashScreen
import com.escom.calmind.ui.screen.WelcomeScreen
import com.escom.calmind.ui.theme.CalmindTheme
import com.escom.calmind.utils.IS_FIRST_TIME

class MainActivity : ComponentActivity() {

    private val isFirstTime = booleanPreferencesKey(IS_FIRST_TIME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalmindTheme {
                val navController = rememberNavController()
                Scaffold {
                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = SplashScreen
                    ) {
                        composable<SplashScreen> {  }
                        composable<WelcomeScreen> {  }
                        composable<LoginScreen> {  }
                    }
                }
            }
        }
    }
}
