package com.escom.calmind.utils

import com.escom.calmind.model.TestResult
import com.escom.calmind.model.User
import com.escom.calmind.ui.route.CongratulationDialog
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomain(): User = User(
    userId = uid,
    email = email,
    displayName = displayName,
    photoUrl = "$photoUrl",
    isEmailVerified = isEmailVerified
)

fun TestResult.toRoute(): CongratulationDialog =
    CongratulationDialog(stressResult, resilienceResult, traumaResult)