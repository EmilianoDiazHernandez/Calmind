package com.escom.calmind.service.impl

import android.util.Log
import com.escom.calmind.model.LoginResult
import com.escom.calmind.service.AuthService
import com.escom.calmind.utils.toLoginResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
) : AuthService {

    override suspend fun singUp(email: String, password: String): AuthResult {
        return auth.createUserWithEmailAndPassword(email, password).await().also {
            Log.i("Sing Up", "${it.user}")
        }
    }

    override suspend fun singIn(email: String, password: String): LoginResult {
        val result = kotlin.runCatching {
            auth.signInWithEmailAndPassword(email, password).await()
        }
        Log.i("Sing Up", "${result.getOrNull()?.user}")
        return result.toLoginResult()
    }
}