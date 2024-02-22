package com.a03.concurrencycontrolproject.domain.user.dto

import com.a03.concurrencycontrolproject.domain.user.model.User

data class UserResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val role: String,
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                email = user.email,
                nickname = user.nickname,
                role = user.role.name
            )
        }
    }
}