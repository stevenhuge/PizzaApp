package com.example.pizzaapp3383.response.account

// AccountResponse.kt
data class AccountResponse(
    val success: Boolean,
    val message: String,
    val username: String? = null,
    val name: String? = null,
    val level: String? = null,
    val password: String? = null
)