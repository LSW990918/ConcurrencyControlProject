package com.a03.concurrencycontrolproject.common.exception

data class AccessDeniedException(val id: Long) : RuntimeException(
    "권한이 없습니다."
)
