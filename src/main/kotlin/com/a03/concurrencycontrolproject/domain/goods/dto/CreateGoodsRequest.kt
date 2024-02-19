package com.a03.concurrencycontrolproject.domain.goods.dto

import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.user.model.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class CreateGoodsRequest(

    val title: String,
    val place: String,
    val runningTime: Int,

    @field: Pattern(
        regexp = dateRegexp,
        message = "Please match date format yyyy-MM-dd HH:mm:ss"
    )
    @Schema(pattern = dateRegexp)
    @JsonProperty("date")
    private val _date: String,

    @field: Pattern(
        regexp = dateRegexp,
        message = "Please match date format yyyy-MM-dd HH:mm:ss"
    )
    @JsonProperty("bookableDate")
    @Schema(pattern = dateRegexp)
    private val _bookableDate: String,

    val ticketAmount: Int,
    val price: Int
) {

    @JsonIgnore
    var categoryId: Long = 0L

    @JsonIgnore
    var userId: Long = 0L

    @JsonIgnore
    val date: LocalDateTime = convertStringToLocalDateTime(this._date)

    @JsonIgnore
    val bookableDate: LocalDateTime = convertStringToLocalDateTime(this._bookableDate)


    private fun convertStringToLocalDateTime(date: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(date, formatter)
    }

    fun to(category: Category, user: User): Goods {
        return Goods(
            title = title,
            runningTime = runningTime,
            date = date,
            bookableDate = bookableDate,
            ticketAmount = ticketAmount,
            price = price,
            place = place,
            category = category,
            user = user,
        )
    }

    companion object {
        private const val dateRegexp =
            "^2[0-9]{3}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) (0[1-9]|1[0-9]|2[0-3]):(0[1-9]|[1-5][0-9]):(0[1-9]|[1-5][0-9])\$"
    }
}
