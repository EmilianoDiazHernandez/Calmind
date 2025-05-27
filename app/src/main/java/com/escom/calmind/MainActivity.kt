package com.escom.calmind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.escom.calmind.ui.composable.SplashScreen
import com.escom.calmind.ui.composable.welcome.WelcomeScreen
import com.escom.calmind.ui.screen.LoginScreen
import com.escom.calmind.ui.screen.SplashScreen
import com.escom.calmind.ui.screen.WelcomeScreen
import com.escom.calmind.ui.theme.CalmindTheme
import com.escom.calmind.ui.viewmodel.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                            SplashScreen { isFirstTime ->
                                navController.navigate(
                                    if (isFirstTime)
                                        WelcomeScreen
                                    else
                                        LoginScreen
                                ) {
                                    popUpTo(SplashScreen) { inclusive = true }
                                }
                            }
                        }
                        composable<WelcomeScreen> {
                            val welcomeViewModel = hiltViewModel<WelcomeViewModel>()
                            WelcomeScreen(
                                name = welcomeViewModel.name,
                                onNameChange = welcomeViewModel::name::set,
                                selectedHobbies = welcomeViewModel.hobbies,
                                onCheckHobby = { hobby -> welcomeViewModel.hobbies.add(hobby) },
                                onUncheckHobby = { hobby -> welcomeViewModel.hobbies.remove(hobby) },
                                schooling = welcomeViewModel.schooling,
                                onChangeSchooling = welcomeViewModel::schooling::set,
                                age = welcomeViewModel.age,
                                onAgeChange = welcomeViewModel::age::set,
                                onClickStartButton = {
                                    welcomeViewModel.onStart()
                                    /*TODO: Navigate to test screen*/
                                }
                            )
                        }
                        composable<LoginScreen> {
                            Text("Login")
                        }
                    }
                }
            }
        }
    }
}
