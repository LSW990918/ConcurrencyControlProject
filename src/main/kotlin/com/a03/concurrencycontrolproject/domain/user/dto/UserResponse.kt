package com.a03.concurrencycontrolproject.domain.user.dto

data class UserResponse (
    val id: Long,
    val email: String,
    val nickname: String,
    val role: String,
)