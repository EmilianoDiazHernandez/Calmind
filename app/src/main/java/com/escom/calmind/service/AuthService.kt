package com.escom.calmind.service

import com.escom.calmind.model.SingInResult
import com.escom.calmind.model.SingUpResult
import com.escom.calmind.model.User

interface AuthService {

    suspend fun singUp(email: String, password: String): SingUpResult
    suspend fun singIn(email: String, password: String): SingInResult
    val currentUser: User?

}