package com.a03.concurrencycontrolproject.common.exception

data class IllegalDateStateException(val first : String, val second:String, val firstDate : String, val secondDate:String): RuntimeException(
    "입력하신 ${second}(${secondDate})는 ${first}(${firstDate})보다 빠를 수 없습니다. "
)
