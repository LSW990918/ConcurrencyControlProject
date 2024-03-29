package com.a03.concurrencycontrolproject.common.exception

import com.a03.concurrencycontrolproject.common.exception.dto.BaseResponse
import com.a03.concurrencycontrolproject.common.exception.dto.ErrorResponse
import com.a03.concurrencycontrolproject.common.exception.status.ResultCode
import com.a03.concurrencycontrolproject.domain.ticket.exception.NotEnoughTicketException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    fun handleRuntimeException(e: RuntimeException): ResponseEntity<ErrorResponse> {
        e.printStackTrace()
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(message = "Internal error"))
    }


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

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(e: AccessDeniedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(IllegalDateStateException::class)
    fun handleIllegalStateDateException(e: IllegalDateStateException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }
        return ResponseEntity(BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotEnoughTicketException::class)
    fun handleNotEnoughTicketException(e: NotEnoughTicketException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(ErrorResponse(e.message))
    }
}