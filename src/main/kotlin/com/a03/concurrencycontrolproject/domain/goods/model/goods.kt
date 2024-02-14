package com.a03.concurrencycontrolproject.domain.goods.model

import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime


@Entity
@SQLDelete(sql = "Update goods SET is_deleted = true WHERE id = ?")
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
    var isDeleted: Boolean = false,
    val bookableDate: LocalDateTime
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
