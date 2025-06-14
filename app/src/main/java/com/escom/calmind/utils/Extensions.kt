package com.escom.calmind.utils

import com.escom.calmind.model.LoginResult
import com.google.firebase.auth.AuthResult

fun Result<AuthResult>.toLoginResult() = when (val result = getOrNull()) {
    null -> LoginResult.Error
    else -> {
        val userId = result.user
        checkNotNull(userId)
        LoginResult.Success
    }
}