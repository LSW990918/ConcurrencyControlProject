package com.a03.concurrencycontrolproject.domain.coupon.model

import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime

@Entity
@Table(name = "coupon")
class Coupon(
    @Column
    val couponName: String,

    @Column
    val couponNumber: String,

    @Column
    var couponAmount: Long,

    @Column
    val couponAvailableDate: LocalDateTime,

    @Column
    val couponExpireDate: LocalDateTime,

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    val goods: Goods,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {

}