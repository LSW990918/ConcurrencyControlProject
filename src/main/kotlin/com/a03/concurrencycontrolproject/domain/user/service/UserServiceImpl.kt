package com.a03.concurrencycontrolproject.domain.user.service

import com.a03.concurrencycontrolproject.common.exception.ModelNotFoundException
import com.a03.concurrencycontrolproject.common.exception.NotExistRoleException
import com.a03.concurrencycontrolproject.common.exception.WrongEmailOrPasswordException
import com.a03.concurrencycontrolproject.common.security.jwt.JwtPlugin
import com.a03.concurrencycontrolproject.domain.user.dto.*
import com.a03.concurrencycontrolproject.domain.user.model.User
import com.a03.concurrencycontrolproject.domain.user.model.checkedEmailOrNicknameExists
import com.a03.concurrencycontrolproject.domain.user.model.checkedLoginPassword
import com.a03.concurrencycontrolproject.domain.user.model.toResponse
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
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


    override fun signup(userRole: UserRole ,request: SignupRequest): UserResponse {
        checkedEmailOrNicknameExists(request.email, request.nickname, userRepository)
        return userRepository.save(
            User(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                nickname = request.nickname,
                isDeleted = false,
                role = userRole
            )
        ).toResponse()
    }

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw WrongEmailOrPasswordException(request.email)
        checkedLoginPassword(user.password, request.password, passwordEncoder)
        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }

    override fun deleteUser(userId: Long) {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)

        userRepository.delete(user)
    }
}