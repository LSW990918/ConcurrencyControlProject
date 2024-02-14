package com.a03.concurrencycontrolproject.domain.goods.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "goods")
data class goods(
    val title: String,
    val runningTime: Int,
    val date: LocalDateTime,
    val ticketAmount: Int,
    val price: Int,
    val place: String,
    //@ManyToOne
    // val category:Category,
    //@ManyToOne
    // val user:User
    val isDeleted:Boolean = true,
    val bookableDate: LocalDateTime,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
