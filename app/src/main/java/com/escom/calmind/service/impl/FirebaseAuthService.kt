package com.escom.calmind.service.impl

import com.escom.calmind.model.SingInResult
import com.escom.calmind.model.SingUpResult
import com.escom.calmind.model.User
import com.escom.calmind.service.AuthService
import com.escom.calmind.utils.toDomain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthService @Inject constructor(private val auth: FirebaseAuth) : AuthService {

    override suspend fun singUp(email: String, password: String): SingUpResult {
        return try {
            with(auth.createUserWithEmailAndPassword(email, password).await()) {
                user?.let {
                    SingUpResult.Success(it.toDomain())
                } ?: SingUpResult.UnknownError
            }
        } catch (e: FirebaseAuthException) {
            when (e.errorCode) {
                "ERROR_EMAIL_ALREADY_IN_USE" -> SingUpResult.UserAlreadyExist(email, password)
                "ERROR_WEAK_PASSWORD" -> SingUpResult.WeakPassword
                else -> SingUpResult.UnknownError
            }
        }
    }

    override suspend fun singIn(email: String, password: String): SingInResult {
        return try {
            with(auth.signInWithEmailAndPassword(email, password).await()) {
                user?.let {
                    SingInResult.Success(it.toDomain())
                } ?: SingInResult.UnknownError
            }
        } catch (e: FirebaseAuthException) {
            when (e.errorCode) {
                "ERROR_USER_NOT_FOUND" -> SingInResult.UserNotFound(email)
                "ERROR_WRONG_PASSWORD" -> SingInResult.WrongPassword
                else -> SingInResult.UnknownError
            }
        }
    }

    override val currentUser: User?
        get() = auth.currentUser?.toDomain()
}
