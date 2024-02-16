package com.a03.concurrencycontrolproject.common.exception

data class NicknameAlreadyExistException(val nickname: String) : RuntimeException(
    "이미 존재하는 닉네임입니다."
)
