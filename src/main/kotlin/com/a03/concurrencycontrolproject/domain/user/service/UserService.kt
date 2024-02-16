package com.a03.concurrencycontrolproject.domain.user.service

import com.a03.concurrencycontrolproject.domain.user.dto.*
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole

interface UserService {
    fun getProfile(userId: Long): UserResponse

    fun updateProfile(userId: Long, request: UpdateProfileRequest): UserResponse

    fun signup(userRole: UserRole ,request: SignupRequest): UserResponse

    fun login(request: LoginRequest): LoginResponse

    fun deleteUser(userId: Long)
}