package com.a03.concurrencycontrolproject.domain.review.model

import com.a03.concurrencycontrolproject.common.BaseTime
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(name = "review")
@SQLDelete(sql = "Update review SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "comment")
    var comment: String,

    @Column(name = "score")
    var score: Int,

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val user: User,

    @ManyToOne
    @JoinColumn(name = "goods_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val goods: Goods

) : BaseTime() {
    init {
        if (this.score < 0 || this.score > 5) {
            throw Exception("평점은 0점 이상 5점 이하로 입력해주세요")
        }
    }

    fun checkAuthorization(requestUser: User) {
        if (requestUser.id != user.id) {
            throw Exception("권한이 없습니다")
        }
    }
}