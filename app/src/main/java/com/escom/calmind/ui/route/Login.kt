package com.escom.calmind.ui.route

import kotlinx.serialization.Serializable

@Serializable
data class Login(val email: String? = null, val password: String? = null)