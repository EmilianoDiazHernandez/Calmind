package com.escom.calmind

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.escom.calmind.ui.route.SplashScreen
import com.escom.calmind.ui.route.buildGraph
import com.escom.calmind.ui.theme.CalmindTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalmindTheme(dynamicColor = false) {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        val currentRoute by navController.currentBackStackEntryAsState()
                        Log.i("current route", currentRoute?.id.orEmpty())
                    }
                ) {
                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = SplashScreen
                    ) {
                        buildGraph(navController)
                    }
                }
            }
        }
    }
}
