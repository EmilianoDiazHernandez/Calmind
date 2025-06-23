package com.escom.calmind.model

sealed class SingInResult {
    data class Success(val userId: String) : SingInResult() //Sing In
    data object UnknownError : SingInResult() //Sing In
    data class UserNotFound(val email: String) : SingInResult() //Sing In
    data object WrongPassword : SingInResult() // Sing In
}
