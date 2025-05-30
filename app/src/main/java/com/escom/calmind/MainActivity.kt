package com.escom.calmind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.escom.calmind.ui.composable.SplashScreen
import com.escom.calmind.ui.composable.TestScreen
import com.escom.calmind.ui.composable.welcome.WelcomeScreen
import com.escom.calmind.ui.screen.LoginScreen
import com.escom.calmind.ui.screen.SplashScreen
import com.escom.calmind.ui.screen.TestScreen
import com.escom.calmind.ui.screen.WelcomeScreen
import com.escom.calmind.ui.theme.CalmindTheme
import com.escom.calmind.ui.viewmodel.StressQuestionsViewModel
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
                                    navController.navigate(TestScreen) {
                                        popUpTo<WelcomeScreen> {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable<TestScreen> {
                            val testViewModel = hiltViewModel<StressQuestionsViewModel>()
                            val currentQuestion by testViewModel.currentQuestion.observeAsState(
                                String()
                            )
                            val currentUser by testViewModel.userDataLiveData.observeAsState(null)
                            val isTestFinished by testViewModel.isFinished.observeAsState(false)
                            TestScreen(
                                currentQuestion = currentQuestion,
                                onQuestionAnswered = testViewModel::onQuestionAnswered,
                                currentUser = currentUser,
                                onTestResultsAvailable = testViewModel::getTestResult,
                                isTestFinished = isTestFinished,
                                onConfirmDialog = {}
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
