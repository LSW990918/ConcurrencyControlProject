package com.a03.concurrencycontrolproject.common.exception

import com.a03.concurrencycontrolproject.common.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(NotExistRoleException::class)
    fun handleNotExistRoleException(e: NotExistRoleException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(EmailAlreadyExistException::class)
    fun handleEmailAlreadyExistException(e: EmailAlreadyExistException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(NicknameAlreadyExistException::class)
    fun handleNicknameAlreadyExistException(e: NicknameAlreadyExistException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(WrongEmailOrPasswordException::class)
    fun handleWrongEmailOrPasswordException(e: WrongEmailOrPasswordException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }
}