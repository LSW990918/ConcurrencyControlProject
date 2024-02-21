package com.a03.concurrencycontrolproject.domain.ticket.exception

data class NotEnoughTicketException(val goodsId: Long) : RuntimeException(
    "$goodsId 상품의 티켓 수량이 부족합니다."
)
