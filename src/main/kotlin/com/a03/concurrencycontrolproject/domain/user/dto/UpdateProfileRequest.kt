package com.a03.concurrencycontrolproject.domain.user.dto

data class UpdateProfileRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val role: String,
)
