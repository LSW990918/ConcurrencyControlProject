package com.a03.concurrencycontrolproject.common.exception.dto

import com.a03.concurrencycontrolproject.common.exception.status.ResultCode

data class BaseResponse<T>(
    val resultCode: String = ResultCode.SUCCESS.name,
    val date: T? = null,
    val message: String = ResultCode.SUCCESS.msg
)
