package com.escom.calmind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.escom.calmind.composable.SplashScreen
import com.escom.calmind.ui.screen.LoginScreen
import com.escom.calmind.ui.screen.SplashScreen
import com.escom.calmind.ui.screen.WelcomeScreen
import com.escom.calmind.ui.theme.CalmindTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalmindTheme(dynamicColor = false) {
                val navController = rememberNavController()
                Scaffold {
                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = SplashScreen
                    ) {
                        composable<SplashScreen> {
                            SplashScreen()
                        }
                        composable<WelcomeScreen> {
                            Text("Welcome")
                        }
                        composable<LoginScreen> {  }
                    }
                }
            }
        }
    }
}
