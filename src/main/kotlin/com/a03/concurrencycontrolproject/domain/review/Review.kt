package com.a03.concurrencycontrolproject.domain.review

import com.a03.concurrencycontrolproject.common.BaseTime
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "review")
@OnDelete(action = OnDeleteAction.CASCADE)
class Review(
    @Column(name = "comment")
    var comment: String,

    @Column(name = "score")
    var score: Int,

    @Column(name = "is_deleted")
    var isDeleted: Boolean,

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    val user: User,

/*    @ManyToOne
    @JoinColumn(name = "goods_id")*/
//    val goods: Goods
):BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}