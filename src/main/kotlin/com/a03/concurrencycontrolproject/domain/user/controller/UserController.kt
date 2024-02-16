package com.a03.concurrencycontrolproject.domain.user.controller

import com.a03.concurrencycontrolproject.common.exception.AccessDeniedException
import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.user.dto.*
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole
import com.a03.concurrencycontrolproject.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @Operation(summary = "프로필 조회")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('MEMBER')")
    @GetMapping("/users/{userId}")
    fun getProfile(
        @PathVariable userId: Long
    ): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getProfile(userId))
    }

    @Operation(summary = "프로필 수정")
    @PutMapping("/users/{userId}")
    fun updateProfile(
        @PathVariable userId: Long,
        @RequestBody request: UpdateProfileRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Unit> {
        if (userId == userPrincipal.id) {
            userService.updateProfile(userId, request)
        } else {
            throw AccessDeniedException(userId)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signup(
        @RequestParam userRole: UserRole,
       @Valid @RequestBody request: SignupRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signup(userRole ,request))
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(request))
    }

    @Operation(summary = "회원탈퇴")
    @DeleteMapping("/users/{userId}")
    fun deleteUser(
        @PathVariable userId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Any> {
        if (userId == userPrincipal.id) {
            userService.deleteUser(userId)
        } else {
            throw AccessDeniedException(userId)
        }
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body("회원탈퇴 완료했습니다.")
    }
}