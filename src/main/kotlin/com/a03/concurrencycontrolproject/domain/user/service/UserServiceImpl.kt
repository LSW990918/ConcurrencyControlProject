package com.a03.concurrencycontrolproject.domain.user.service

import com.a03.concurrencycontrolproject.common.exception.ModelNotFoundException
import com.a03.concurrencycontrolproject.common.exception.NotExistRoleException
import com.a03.concurrencycontrolproject.common.exception.WrongEmailOrPasswordException
import com.a03.concurrencycontrolproject.domain.user.dto.*
import com.a03.concurrencycontrolproject.domain.user.model.User
import com.a03.concurrencycontrolproject.domain.user.model.checkedEmailOrNicknameExists
import com.a03.concurrencycontrolproject.domain.user.model.checkedLoginPassword
import com.a03.concurrencycontrolproject.domain.user.model.toResponse
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    override fun getProfile(userId: Long): UserResponse {
        val profile = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)

        return profile.toResponse()
    }

    override fun updateProfile(userId: Long, request: UpdateProfileRequest): UserResponse {
        val profile = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        profile.toUpdate(request)
        userRepository.save(profile)

        return profile.toResponse()
    }


    override fun signup(request: SignupRequest): UserResponse {
        checkedEmailOrNicknameExists(request.email, request.nickname, userRepository)
        return userRepository.save(
            User(
                email = request.email,
                password = request.password,
                nickname = request.nickname,
                isDeleted = false,
                role = when (request.role) {
                    "ADMIN" -> UserRole.ADMIN
                    "SELLER" -> UserRole.SELLER
                    "MEMBER" -> UserRole.MEMBER
                    else -> throw NotExistRoleException(request.role)
                }
            )
        ).toResponse()
    }

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw WrongEmailOrPasswordException(request.email)
        checkedLoginPassword(user.password, request.password)
        return LoginResponse(
            accessToken = "dddd"
        )
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

}