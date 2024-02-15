package com.a03.concurrencycontrolproject.domain.user.service

import com.a03.concurrencycontrolproject.domain.user.dto.*

interface UserService {
    fun getProfile(userId: Long): UserResponse

    fun updateProfile(userId: Long, request: UpdateProfileRequest): UserResponse

    fun signup(request: SignupRequest): UserResponse

    fun login(request: LoginRequest): LoginResponse

    fun logout()
}