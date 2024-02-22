package com.a03.concurrencycontrolproject.domain.ticket.service


import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.category.repository.CategoryRepository
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.goods.repository.GoodsRepository
import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.exception.NotEnoughTicketException
import com.a03.concurrencycontrolproject.domain.user.model.User
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
@ExtendWith(MockKExtension::class)
@ActiveProfiles("test")
class TicketServiceTest(
    @Autowired val userRepository: UserRepository,
    @Autowired val goodsRepository: GoodsRepository,
    @Autowired val categoryRepository: CategoryRepository,
    @Autowired val ticketService: TicketService
) {

    @Test
    @DisplayName("티켓 테스트")
    fun `ticket test`() {
        //given 멀티 스레드 환경에서 굿즈의 티켓 총 갯수가 50개이고
        val user = User(
            email = "test@test.com",
            password = "a1234!",
            role = UserRole.SELLER,
            nickname = "1234"
        )

        var getUser = userRepository.save(user)

        val category = Category(
            title = "test title"
        )
        val getCategory = categoryRepository.save(category)

        val goods = Goods(
            title = "123",
            runningTime = 10,
            date = LocalDateTime.now(),
            bookableDate = LocalDateTime.now(),
            ticketAmount = 50,
            place = "123",
            price = 1000,
            category = getCategory,
            user = getUser
        )
        val getGoods = goodsRepository.save(goods)

        val threadCount = 100

        val executorService = Executors.newFixedThreadPool(100)
        val countDownLatch = CountDownLatch(threadCount)
        val createTicketReq = CreateTicketRequest(goodsId = getGoods.id!!)
        var success = 0
        var fail = 0

        //when 100개의 스레드로 동시에 티켓을 구매했을때
        repeat(threadCount) {
            executorService.submit {
                try {
                    ticketService.createTicket(user.id!!, createTicketReq)
                    success++
                } catch (e: NotEnoughTicketException) {
                    fail++
                } finally {
                    countDownLatch.countDown()
                }
            }
        }
        countDownLatch.await()

        // 시도한 횟수랑 = 실패 + 성공 횟수 같아야함.
        // 성공 횟수가 100이 아닌거는 동시성 문제해결.
        println("success : $success")
        println("fail : $fail")

        //then 성공50 실패50 이여야한다.
        Assertions.assertThat(success).isEqualTo(goods.ticketAmount)
        Assertions.assertThat(fail).isEqualTo(threadCount - goods.ticketAmount)
    }
}