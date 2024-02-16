package com.a03.concurrencycontrolproject.domain.category.model

import com.a03.concurrencycontrolproject.common.BaseTime
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.user.model.User
import jakarta.persistence.*
import jakarta.persistence.CascadeType
import jakarta.persistence.Table
import org.hibernate.annotations.*

@Entity
@SQLDelete(sql = "UPDATE category SET is_deleted = true WHERE id = ?") // DELETE 쿼리 날아올 시 대신 실행
@SQLRestriction("is_deleted = false")
@Table(name = "category")
class Category(

    @Column(name = "title")
    var title: String,

    @Column(name = "is_deleted")
    val isDeleted: Boolean = false,

    @OneToMany(
        mappedBy = "category",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val goodsList: MutableList<Goods> = mutableListOf(),

    @OneToMany(
        mappedBy = "category",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val userList: MutableList<User> = mutableListOf()

) : BaseTime() {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}