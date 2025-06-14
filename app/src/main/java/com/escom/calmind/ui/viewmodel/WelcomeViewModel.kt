package com.escom.calmind.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.escom.calmind.model.UserData
import com.escom.calmind.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    var name by mutableStateOf(String())
    var age by mutableStateOf(String())
    var schooling by mutableStateOf(String())
    var hobbies = mutableStateListOf<String>()

    fun onStart() {
        val new = UserData(
            name = name.trim(),
            age = age.toInt(),
            hobbies = hobbies,
            schooling = schooling
        )
        userDataRepository.set(new)
    }
}