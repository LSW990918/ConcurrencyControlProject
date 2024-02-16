package com.a03.concurrencycontrolproject.domain.goods.model

import com.a03.concurrencycontrolproject.common.BaseTime
import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id")
    var category: Category,

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseTime() {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false
//  Todo dependency
//    @OneToMany(fetch = FetchType.LAZY)
//    val ticket:Ticket

}
