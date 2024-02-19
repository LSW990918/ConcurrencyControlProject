package com.a03.concurrencycontrolproject.domain.ticket.service

import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.goods.repository.GoodsRepository
import com.a03.concurrencycontrolproject.domain.ticket.controller.TicketController
import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket
import com.a03.concurrencycontrolproject.domain.ticket.repository.TicketRepository
import com.a03.concurrencycontrolproject.domain.user.model.User
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
@ExtendWith(MockKExtension::class)
class TicketServiceTest : BehaviorSpec({
    extensions(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val ticketRepository = mockk<TicketRepository>()
    val userRepository = mockk<UserRepository>()
    val goodsRepository = mockk<GoodsRepository>()


    val ticketService = TicketServiceImpl(ticketRepository, goodsRepository, userRepository)
    val ticketController = TicketController(ticketService)

    val goods = Goods(
        title = "123",
        runningTime = 10,
        date = LocalDateTime.now(),
        bookableDate = LocalDateTime.now(),
        ticketAmount = 100,
        place = "123",
        price = 1000,
        category = Category(
            title = "123"
        ),
        user = User(
            email = "test@test.com",
            password = "a1234!",
            role = UserRole.SELLER,
            nickname = "1234"
        )
    )
    val user = User(
        email = "test@test.com",
        password = "a1234!",
        role = UserRole.SELLER,
        nickname = "1234"
    )
    val ticket = Ticket(
        goods = goods,
        user = user
    )

    val createTicketReq = CreateTicketRequest(goodsId = 1L)
    every { goodsRepository.findByIdOrNull(any()) } returns goods
    every { userRepository.findByIdOrNull(any()) } returns user
    every { ticketRepository.save(any()) } returns ticket

    Given("구매가능한 티켓이 있을 때") {
        val targetTicket = createTicketReq

        When("티켓을 구매하면") {
            val result = ticketController.createTicket(
                userPrincipal = UserPrincipal(
                    id = 1L,
                    email = "test@gmail.com",
                    roles = setOf("ADMIN")
                ), request = targetTicket
            )

            Then("201 코드를 반환한다") {
                result.statusCode shouldBe HttpStatus.CREATED
            }
        }
    }

    Given("멀티스레드 환경에서"){
        val threadCount = 100
        val executorService = Executors.newFixedThreadPool(20)
        val countDownLatch = CountDownLatch(threadCount)

        When("동시에 총 100개의 티켓을 구매하면") {
            repeat(threadCount) {
                executorService.submit {
                    try {
                        ticketService.createTicket(user.id!!, createTicketReq)
                    } finally {
                        countDownLatch.countDown()
                    }
                }
            }
            countDownLatch.await()

            val viewCount = goods.ticketAmount-goods.ticket.size

            Then("티켓의 남은 양은 0이 되어야 한다.") {
                assertThat(viewCount).isEqualTo(100)
            }
        }
    }

})