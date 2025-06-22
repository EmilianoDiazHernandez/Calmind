package com.escom.calmind.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escom.calmind.model.SingUpResult
import com.escom.calmind.model.User
import com.escom.calmind.repository.UserDataRepository
import com.escom.calmind.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userDataRepository: UserDataRepository,
    private val authService: AuthService
) : ViewModel() {

    var email by mutableStateOf(String())
    var password by mutableStateOf(String())
    var isLoading by mutableStateOf(false)
        private set
    var isError by mutableStateOf(false)
        private set
    var isWeakPassword by mutableStateOf(false)
        private set

    val currentUser = userDataRepository.get()

    fun singUp(onSuccess: (User) -> Unit, onUserExist: (email: String, password: String) -> Unit) {
        isLoading = true
        viewModelScope.launch {
            delay(1000L)
            when(val result = authService.singUp(email, password)) {
                is SingUpResult.Success -> onSuccess(result.user)
                is SingUpResult.UnknownError -> isError = true
                is SingUpResult.UserAlreadyExist -> onUserExist(result.email, result.password)
                SingUpResult.WeakPassword -> isWeakPassword = true
            }
            isLoading = false
        }
    }

    fun singIn() {

    }

}
