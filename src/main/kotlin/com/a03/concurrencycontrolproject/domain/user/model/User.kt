package com.a03.concurrencycontrolproject.domain.user.model

import com.a03.concurrencycontrolproject.common.BaseTime
import com.a03.concurrencycontrolproject.common.exception.EmailAlreadyExistException
import com.a03.concurrencycontrolproject.common.exception.NicknameAlreadyExistException
import com.a03.concurrencycontrolproject.common.exception.NotExistRoleException
import com.a03.concurrencycontrolproject.common.exception.WrongEmailOrPasswordException
import com.a03.concurrencycontrolproject.domain.user.dto.UpdateProfileRequest
import com.a03.concurrencycontrolproject.domain.user.dto.UserResponse
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
@SQLDelete(sql = "UPDATE app_user SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "app_user")
class User(
    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: UserRole,

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "is_deleted")
    val isDeleted: Boolean = false

    ) : BaseTime() {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toUpdate(request: UpdateProfileRequest) {
        email = request.email
        password = request.password
        nickname = request.nickname
        role =  when (request.role) {
            "ADMIN" -> UserRole.ADMIN
            "SELLER" -> UserRole.SELLER
            "MEMBER" -> UserRole.MEMBER
            else  -> throw NotExistRoleException(request.role)
        }
    }
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        nickname = nickname,
        role = role.name
    )
}

fun checkedEmailOrNicknameExists(email: String, nickname: String, userRepository: UserRepository) {
    if (userRepository.existsByEmail(email)) {
        throw EmailAlreadyExistException(email)
    }

    if (userRepository.existsByNickname(nickname)) {
        throw NicknameAlreadyExistException(nickname)
    }
}

fun checkedLoginPassword(password: String, inputPassword: String) {
//    if(!passwordEncoder.matches(inputPassword, password)) {
//        throw WrongEmailOrPasswordException(inputPassword)
//    }
      if (password != inputPassword) {
          throw WrongEmailOrPasswordException(inputPassword)
      }
}