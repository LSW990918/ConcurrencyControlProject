package com.a03.concurrencycontrolproject.domain.goods.dto

import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class CreateGoodsRequest(

    val title: String,
    val place: String,
    val runningTime: Int,
    @field: Pattern(
        regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) (0[1-9]|1[0-9]|2[0-3]):(0[1-9]|[1-5][0-9]):(0[1-9]|[1-5][0-9])",
        message = "Please match date format yyyy-MM-dd HH:mm:ss"
    )
    private val date: String,
    @field: Pattern(
        regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) (0[1-9]|1[0-9]|2[0-3]):(0[1-9]|[1-5][0-9]):(0[1-9]|[1-5][0-9])",
        message = "Please match date format yyyy-MM-dd HH:mm:ss"
    )
    private val bookableDate: String,
    val ticketAmount: Int,
    val price: Int
) {

    val convertDate: LocalDateTime
    val convertBookableDate: LocalDateTime
    var categoryId: Long = 0L
    var userId: Long = 0L

    init {
        convertDate = convertStringToLocalDateTime(this.date)
        convertBookableDate = convertStringToLocalDateTime(this.bookableDate)
    }

    private fun convertStringToLocalDateTime(date: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(date, formatter)
    }

}
