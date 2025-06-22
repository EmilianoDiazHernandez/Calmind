package com.escom.calmind.model

data class User(
    val userId: String,
    val email: String?,
    val displayName: String?,
    val photoUrl: String?,
    val isEmailVerified: Boolean,
)
