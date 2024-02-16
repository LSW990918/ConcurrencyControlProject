package com.a03.concurrencycontrolproject.domain.ticket.model

import com.a03.concurrencycontrolproject.common.BaseTime
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.user.model.User
import jakarta.persistence.*

@Entity
@Table(name = "ticket")
class Ticket(

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    val user: User,

    @ManyToOne
    @JoinColumn(name = "goods_id")
    val goods: Goods
): BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}