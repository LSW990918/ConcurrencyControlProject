package com.a03.concurrencycontrolproject.domain.goods.dto

import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.user.model.User
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class CreateGoodsRequest(

    val title: String,
    val place: String,
    val runningTime: Int,
    @field: Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\$",
        message = "Please match date format yyyy-MM-dd HH:mm:ss"
    )
    private val date: String,
    @field: Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\$",
        message = "Please match date format yyyy-MM-dd HH:mm:ss"
    )
    private val bookableDate: String,
//    @field: Pattern(
//        regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) (0[1-9]|1[0-9]|2[0-3]):(0[1-9]|[1-5][0-9]):(0[1-9]|[1-5][0-9])\$",
//        message = "Please match date format yyyy-MM-dd HH:mm:ss"
//    )
//    private val date: String,
//    @field: Pattern(
//        regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) (0[1-9]|1[0-9]|2[0-3]):(0[1-9]|[1-5][0-9]):(0[1-9]|[1-5][0-9])\$",
//        message = "Please match date format yyyy-MM-dd HH:mm:ss"
//    )
//    private val bookableDate: String,
    val ticketAmount: Int,
    val price: Int
) {

    @JsonIgnore
    val convertDate: LocalDateTime
    @JsonIgnore
    val convertBookableDate: LocalDateTime
    @JsonIgnore
    var categoryId: Long = 0L
    @JsonIgnore
    var userId: Long = 0L

    init {
        convertDate = convertStringToLocalDateTime(this.date)
        convertBookableDate = convertStringToLocalDateTime(this.bookableDate)
    }

    private fun convertStringToLocalDateTime(date: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(date, formatter)
    }

    fun to(category: Category, user: User): Goods {
        return Goods(
            title = title,
            runningTime = runningTime,
            date = convertDate,
            bookableDate = convertBookableDate,
            ticketAmount = ticketAmount,
            price = price,
            place = place,
            category = category,
            user = user,
        )
    }

}
