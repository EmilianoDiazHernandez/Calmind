package com.escom.calmind.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escom.calmind.repository.UserDataRepository
import com.escom.calmind.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userDataRepository: UserDataRepository,
    private val authService: AuthService
) : ViewModel() {

    var email by mutableStateOf(String())
    var password by mutableStateOf(String())
    private var _isLoading by mutableStateOf(false)
    val isLoading = _isLoading

    val currentUser = userDataRepository.get()

    fun singUp() {
        _isLoading = true
        viewModelScope.launch {
            authService.singUp(email, password)
            _isLoading = false
        }
    }

}