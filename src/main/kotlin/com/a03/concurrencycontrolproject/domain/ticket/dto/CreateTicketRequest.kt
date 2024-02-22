package com.a03.concurrencycontrolproject.domain.ticket.dto

import com.a03.concurrencycontrolproject.common.aop.ConcurrencyControlDto
import com.fasterxml.jackson.annotation.JsonIgnore

data class CreateTicketRequest(
    val goodsId: Long,
    @JsonIgnore
    override val targetId: Long = goodsId
) : ConcurrencyControlDto