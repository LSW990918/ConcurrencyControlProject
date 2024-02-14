package com.a03.concurrencycontrolproject.domain.user.model

import com.a03.concurrencycontrolproject.common.BaseTime
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: UserRole,

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false

    ) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}