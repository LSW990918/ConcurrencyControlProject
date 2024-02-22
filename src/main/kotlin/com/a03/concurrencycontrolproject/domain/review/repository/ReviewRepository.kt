package com.a03.concurrencycontrolproject.domain.review.repository

import com.a03.concurrencycontrolproject.domain.review.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long>, ReviewQueryDslRepository {
}
