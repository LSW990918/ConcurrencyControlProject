package com.a03.concurrencycontrolproject.domain.user.dto

data class LoginRequest(
    val email: String,
    val password: String,
)
