package com.a03.concurrencycontrolproject.domain.user.service

import com.a03.concurrencycontrolproject.domain.user.dto.LoginRequest
import com.a03.concurrencycontrolproject.domain.user.dto.LoginResponse
import com.a03.concurrencycontrolproject.domain.user.dto.SignupRequest
import com.a03.concurrencycontrolproject.domain.user.dto.UserResponse
import org.springframework.stereotype.Service

@Service
class UserServiceImpl: UserService {
    override fun getProfile(userId: Long): UserResponse {
        TODO("Not yet implemented")
    }

    override fun updateProfile(userId: Long) {
        TODO("Not yet implemented")
    }

    override fun signup(request: SignupRequest) {
        TODO("Not yet implemented")
    }

    override fun login(request: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

}