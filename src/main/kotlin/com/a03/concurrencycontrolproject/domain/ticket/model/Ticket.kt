package com.a03.concurrencycontrolproject.domain.ticket.model

import com.a03.concurrencycontrolproject.common.BaseTime
import com.a03.concurrencycontrolproject.common.exception.AccessDeniedException
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@SQLDelete(sql = "UPDATE ticket SET is_deleted = true WHERE id = ?") // DELETE 쿼리 날아올 시 대신 실행
@SQLRestriction("is_deleted = false")
@Table(name = "ticket")
class Ticket(

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val user: User,

    @ManyToOne
    @JoinColumn(name = "goods_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val goods: Goods,

    @Column(name = "is_deleted", nullable = false)
    val isDeleted: Boolean = false

) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

fun accessUser(userId: Long, requestUserId: Long) {
    if (userId != requestUserId) throw AccessDeniedException(requestUserId)
}