package com.escom.calmind.model

sealed class SingUpResult {
    data object UnknownError : SingUpResult() //Both
    data class Success(val user: User) : SingUpResult() //Both
    data object WeakPassword : SingUpResult() // Sing Up
    data class UserAlreadyExist(val email: String, val password: String) : SingUpResult()
}