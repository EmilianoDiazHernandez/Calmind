package com.escom.calmind.service.impl

import com.escom.calmind.model.SingInResult
import com.escom.calmind.model.SingUpResult
import com.escom.calmind.model.UserData
import com.escom.calmind.service.AuthService
import com.escom.calmind.utils.USER_COLLECTION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthService {

    override suspend fun singUp(data: UserData, email: String, password: String): SingUpResult {
        return try {
            with(auth.createUserWithEmailAndPassword(email, password).await()) {
                user?.let {
                    db.collection(USER_COLLECTION).document(it.uid).set(data)
                    SingUpResult.Success(it.uid)
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
                user?.let { SingInResult.Success(it.uid) } ?: SingInResult.UnknownError
            }
        } catch (e: FirebaseAuthException) {
            when (e.errorCode) {
                "ERROR_USER_NOT_FOUND" -> SingInResult.UserNotFound(email)
                "ERROR_WRONG_PASSWORD" -> SingInResult.WrongPassword
                else -> SingInResult.UnknownError
            }
        }
    }

    override val currentUserId: String?
        get() = auth.currentUser?.uid

    override suspend fun retrieveUserById(id: String): UserData =
        db.collection(USER_COLLECTION).document(id).get().await().toObject(UserData::class.java)!!

}
