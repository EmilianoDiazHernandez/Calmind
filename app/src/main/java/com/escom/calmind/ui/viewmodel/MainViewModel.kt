package com.escom.calmind.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escom.calmind.model.UserData
import com.escom.calmind.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {

    private val _currentUser = MutableStateFlow<UserData?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _currentTextJournal = MutableStateFlow(String())
    val currentTextJournal = _currentTextJournal.asStateFlow()

    fun retrieveUserById(userId: String) {
        viewModelScope.launch {
            _currentUser.value = authService.retrieveUserById(userId)
        }
    }

    fun onJournalTextChange(newText: String) {
        _currentTextJournal.value = newText
    }

}