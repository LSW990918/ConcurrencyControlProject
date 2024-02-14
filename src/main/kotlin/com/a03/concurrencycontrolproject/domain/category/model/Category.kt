package com.a03.concurrencycontrolproject.domain.category.model

import com.a03.concurrencycontrolproject.common.BaseTime
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity
@SQLDelete(sql = "UPDATE category SET is_deleted = true WHERE id = ?") // DELETE 쿼리 날아올 시 대신 실행
@Where(clause = "is_deleted = false")
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "category")
class Category(

    @Column(name = "title")
    val title: String,

    @Column(name = "is_deleted")
    val isDeleted: Boolean = false

) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}