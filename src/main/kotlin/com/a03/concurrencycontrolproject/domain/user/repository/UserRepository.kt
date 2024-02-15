package com.a03.concurrencycontrolproject.domain.user.repository

import com.a03.concurrencycontrolproject.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>