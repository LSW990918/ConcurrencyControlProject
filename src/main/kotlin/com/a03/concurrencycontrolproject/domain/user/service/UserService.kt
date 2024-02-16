package com.a03.concurrencycontrolproject.domain.user.service

import com.a03.concurrencycontrolproject.domain.user.dto.*
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole

interface UserService {
    fun getProfile(userId: Long): UserResponse

    fun updateProfile(userId: Long, request: UpdateProfileRequest)

    fun signup(userRole: UserRole ,request: SignupRequest)

    fun login(request: LoginRequest): LoginResponse

    fun deleteUser(userId: Long)
}