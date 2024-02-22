package com.a03.concurrencycontrolproject.common.exception

data class NotExistRoleException(val role: String) : RuntimeException(
    "입력하신 $role 은 존재하지 않습니다"
)
