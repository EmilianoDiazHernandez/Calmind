package com.escom.calmind.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.escom.calmind.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(authService: AuthService) : ViewModel() {
    private val _currentUser = MutableStateFlow(authService.currentUserId)
    val currentUser = _currentUser.asStateFlow()
}