package com.a03.concurrencycontrolproject.domain.user.model

import com.a03.concurrencycontrolproject.common.BaseTime
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@SQLDelete(sql = "UPDATE app_user SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "app_user")
class User(
    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: UserRole,

    @Column(name = "is_deleted")
    val isDeleted: Boolean = false

    ) : BaseTime() {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}