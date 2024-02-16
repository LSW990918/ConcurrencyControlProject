package com.a03.concurrencycontrolproject.domain.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class SignupRequest(
    @field: NotBlank
    @field: Pattern(
        regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$",
        message = "이메일의 형식에 맞게 입력해주세요"
    )
    @JsonProperty("email")
    private val _email: String?,

    @field: NotBlank
    @field: Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{4,15}\$",
        message = "영문, 숫자, 특수문자를 포함한 4~15자리로 입력해주세요"
    )
    @JsonProperty("password")
    private val _password: String?,

    @field: NotBlank
    @field: Pattern(
        regexp = "^([a-z]|[A-Z])(?=.*[0-9])[a-zA-Z0-9]{3,10}\$",
        message = "영문, 숫자를 포함한 3~10자리로 입력해주세요"
    )
    @JsonProperty("nickname")
    private val _nickname: String?,
) {
    val email: String
        get() = _email!!

    val password: String
        get() = _password!!

    val nickname: String
        get() = _nickname!!
}
