package com.escom.calmind.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escom.calmind.model.EmotionResponse
import com.escom.calmind.model.UserData
import com.escom.calmind.service.AuthService
import com.escom.calmind.service.GratitudeJournalService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authService: AuthService,
    private val gratitudeJournalService: GratitudeJournalService
) : ViewModel() {

    private val _currentUser = MutableStateFlow<UserData?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _emotions = MutableStateFlow<List<EmotionResponse>>(emptyList())
    val emotions = _emotions.asStateFlow()

    private val _currentTextJournal = MutableStateFlow(String())
    val currentTextJournal = _currentTextJournal.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun retrieveUserById(userId: String) {
        viewModelScope.launch {
            _currentUser.value = authService.retrieveUserById(userId)
        }
    }

    fun onJournalTextChange(newText: String) {
        _currentTextJournal.value = newText
    }

    fun saveGratitudeJournal(userId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            gratitudeJournalService.save(userId, _currentTextJournal.value).also {
                _emotions.value = it
                _isLoading.value = false
            }
        }
    }

    fun clearEmotions() {
        _emotions.value = emptyList()
    }

}