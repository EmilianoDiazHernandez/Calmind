package com.escom.calmind.model

sealed class LoginResult {
    data object Error : LoginResult()
    data object Success : LoginResult()
}