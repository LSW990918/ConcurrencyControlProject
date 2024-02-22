package com.a03.concurrencycontrolproject.common.exception

data class WrongEmailOrPasswordException(val emailOrPassword: String) : RuntimeException(
    "닉네임 또는 패스워드를 확인해 주세요"
)
