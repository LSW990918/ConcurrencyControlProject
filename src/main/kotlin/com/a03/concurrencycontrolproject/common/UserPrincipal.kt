package com.a03.concurrencycontrolproject.common

data class UserPrincipal(
    val id: Long,
    val name: String,
) {
    fun to() = User (
        id = id,
        nickName = nickName,
//        password = password,

    )
}
