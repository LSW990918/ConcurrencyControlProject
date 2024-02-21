package com.a03.concurrencycontrolproject.domain.coupon.model

import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime

@Entity
@Table(name = "coupon")
class Coupon(
    @Column(name = "coupon_name")
    val couponName: String,

    @Column(name = "coupon_number")
    var couponNumber: String,

    @Column(name = "coupon_amount")
    var couponAmount: Long,

    @Column(name = "coupon_available_date")
    val couponAvailableDate: LocalDateTime,

    @Column(name = "coupon_expire_date")
    val couponExpireDate: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "goods_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val goods: Goods,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {

}