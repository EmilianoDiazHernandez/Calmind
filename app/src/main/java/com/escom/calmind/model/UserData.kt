package com.escom.calmind.model

data class UserData(
    val name: String = "",
    val age: Int = 0,
    val hobbies: List<String> = emptyList(),
    val schooling: String = "",
    val gratitudeDays: Int = 0
)