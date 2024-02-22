package com.a03.concurrencycontrolproject.domain.goods.model

import com.a03.concurrencycontrolproject.common.BaseTime
import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.review.model.Review
import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket
import com.a03.concurrencycontrolproject.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime


@Entity
@SQLDelete(sql = "Update goods SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "goods")
class Goods(
    @Column(name = "title")
    var title: String,
    @Column(name = "running_time")
    var runningTime: Int,
    @Column(name = "date")
    val date: LocalDateTime,
    @Column(name = "bookable_date")
    val bookableDate: LocalDateTime,
    @Column(name = "ticket_amount")
    val ticketAmount: Int,
    @Column(name = "price")
    var price: Int,
    @Column(name = "place")
    val place: String,

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseTime() {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false

    @OneToMany(mappedBy = "goods", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var ticket: MutableList<Ticket> = mutableListOf()

    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var review: MutableList<Review> = mutableListOf()

}
