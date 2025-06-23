package com.escom.calmind.service

import com.escom.calmind.model.SingInResult
import com.escom.calmind.model.SingUpResult
import com.escom.calmind.model.UserData

interface AuthService {

    suspend fun singUp(data: UserData, email: String, password: String): SingUpResult
    suspend fun singIn(email: String, password: String): SingInResult
    val currentUserId: String?
    suspend fun retrieveUserById(id: String): UserData

}