package com.escom.calmind.ui.route

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.escom.calmind.R
import com.escom.calmind.model.TestResult
import com.escom.calmind.model.TopBarTitle
import com.escom.calmind.ui.composable.CongratulationDialog
import com.escom.calmind.ui.composable.LoadingFullScreen
import com.escom.calmind.ui.composable.MainScreen
import com.escom.calmind.ui.composable.SplashScreen
import com.escom.calmind.ui.composable.TestScreen
import com.escom.calmind.ui.composable.welcome.WelcomeScreen
import com.escom.calmind.ui.viewmodel.LoginViewModel
import com.escom.calmind.ui.viewmodel.MainViewModel
import com.escom.calmind.ui.viewmodel.SplashScreenViewModel
import com.escom.calmind.ui.viewmodel.StressQuestionsViewModel
import com.escom.calmind.ui.viewmodel.WelcomeViewModel
import com.escom.calmind.utils.UpdateIsFirstTime
import com.escom.calmind.utils.toRoute

fun NavGraphBuilder.buildGraph(
    navController: NavController,
    onUpdateTopBar: (TopBarTitle) -> Unit
) {
    composable<SplashScreen> {
        val splashViewModel = hiltViewModel<SplashScreenViewModel>()
        val userExist by splashViewModel.currentUser.collectAsState()
        SplashScreen { isFirstTime ->
            navController.navigate(
                if (isFirstTime)
                    WelcomeScreen
                else
                    userExist?.let { Main(it) } ?: LoginScreen()
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
                    popUpTo<WelcomeScreen> { inclusive = true }
                }
            }
        )
    }
    composable<TestScreen> {
        val testViewModel = hiltViewModel<StressQuestionsViewModel>()
        val currentQuestion by testViewModel.currentQuestion.observeAsState(String())
        TestScreen(
            currentQuestion = currentQuestion,
            onQuestionAnswered = {
                testViewModel.onQuestionAnswered(it) {
                    navController.navigate(testViewModel.testResult.toRoute()) {
                        popUpTo<TestScreen> { inclusive = true }
                    }
                }
            }
        )
    }
    dialog<CongratulationDialog>(
        dialogProperties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    ) { backStackEntry ->
        val congratulationDialog =
            backStackEntry.toRoute<CongratulationDialog>()
        val testResult = with(congratulationDialog) {
            TestResult(stressResult, resilienceResult, traumaResult)
        }
        val loginViewModel = hiltViewModel<LoginViewModel>()
        val pageState = rememberPagerState(pageCount = { 5 })
        val currentUserData by loginViewModel.currentUser.observeAsState()
        CongratulationDialog(
            testResult = testResult,
            currentUser = currentUserData,
            onConfirmDialog = {
                loginViewModel.singUp(
                    onSuccess = { newUserId ->
                        navController.navigate(Main(newUserId)) {
                            popUpTo<CongratulationDialog> { inclusive = true }
                        }
                    },
                    onUserExist = { email, password ->
                        navController.navigate(LoginScreen(email, password)) {
                            popUpTo<CongratulationDialog> { inclusive = true }
                        }
                    }
                )
            },
            pageState = pageState,
            email = loginViewModel.email,
            onEmailChange = loginViewModel::email::set,
            password = loginViewModel.password,
            onPasswordChange = loginViewModel::password::set,
            isLoading = loginViewModel.isLoading,
            isError = loginViewModel.isError,
            isWeakPassword = loginViewModel.isWeakPassword
        )
    }
    composable<LoginScreen> { backStackEntry ->
        val (email, password) = backStackEntry.toRoute<LoginScreen>()
        val loginViewModel = hiltViewModel<LoginViewModel>()
        if (email != null && password != null) {
            UpdateIsFirstTime()
            loginViewModel.email = email
            loginViewModel.password = password
        }
        Text("Login")
    }
    composable<Main> { backStackEntry ->
        UpdateIsFirstTime()
        val userId = backStackEntry.toRoute<Main>().userId
        val mainViewModel = hiltViewModel<MainViewModel>()
        val currentUser by mainViewModel.currentUser.collectAsState()
        mainViewModel.retrieveUserById(userId)
        currentUser?.let {
            onUpdateTopBar(TopBarTitle(R.string.welcome, it.name))
            MainScreen(userData = it)
        } ?: LoadingFullScreen()
    }
}
