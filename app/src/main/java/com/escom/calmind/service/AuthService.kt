package com.escom.calmind.service

import com.escom.calmind.model.LoginResult
import com.google.firebase.auth.AuthResult

interface AuthService {
    suspend fun singUp(email: String, password: String): AuthResult?
    suspend fun singIn(email: String, password: String): LoginResult
}