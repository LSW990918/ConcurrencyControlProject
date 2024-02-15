package com.a03.concurrencycontrolproject.domain.user.controller

import com.a03.concurrencycontrolproject.domain.user.dto.*
import com.a03.concurrencycontrolproject.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @Operation(summary = "프로필 조회")
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
        @RequestBody request: UpdateProfileRequest
    ): ResponseEntity<UserResponse> {
        userService.updateProfile(userId, request)
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signup(
        @RequestBody request: SignupRequest
    ): ResponseEntity<UserResponse> {
        userService.signup(request)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build()
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

    @Operation(summary = "로그아웃")
    @DeleteMapping("/logout")
    fun logout(): ResponseEntity<Unit> {
        userService.logout()
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }
}