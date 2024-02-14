package com.a03.concurrencycontrolproject.domain.goods.model

import com.a03.concurrencycontrolproject.common.BaseTime
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime


@Entity
@SQLDelete(sql = "Update goods SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "goods")
class goods(
    @Column(name = "title")
    val title: String,
    @Column(name = "running_time")
    val runningTime: Int,
    @Column(name = "date")
    val date: LocalDateTime,
    @Column(name = "ticket_amount")
    val ticketAmount: Int,
    @Column(name = "price")
    val price: Int,
    @Column(name = "place")
    val place: String,
    //@ManyToOne
    // val category:Category,
    //@ManyToOne
    // val user:User
    @Column(name = "is_deleted")
    val isDeleted: Boolean = false,
    @Column(name = "bookable_date")
    val bookableDate: LocalDateTime
) : BaseTime() {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
