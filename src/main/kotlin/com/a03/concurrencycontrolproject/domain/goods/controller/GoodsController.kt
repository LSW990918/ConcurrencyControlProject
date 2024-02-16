package com.a03.concurrencycontrolproject.domain.goods.controller

import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.goods.dto.CreateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.GoodsResponse
import com.a03.concurrencycontrolproject.domain.goods.dto.UpdateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.service.GoodsService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/categories/{categoryId}/goods")
class GoodsController(
    val goodsService: GoodsService
) {

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    fun createGoods(
        @PathVariable categoryId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @Valid @RequestBody createGoodsRequest: CreateGoodsRequest
    ): ResponseEntity<Unit> {
        createGoodsRequest.categoryId = categoryId
        createGoodsRequest.userId = userPrincipal.id
        goodsService.createGoods(createGoodsRequest)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build()
    }

    @PutMapping("/{goodsId}")
    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    fun updateGoods(
        @PathVariable categoryId: Long,
        @PathVariable goodsId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody updateGoodsRequest: UpdateGoodsRequest
    ): ResponseEntity<Unit> {
        updateGoodsRequest.goodsId = goodsId
        updateGoodsRequest.userId = userPrincipal.id
        return goodsService.updateGoods(updateGoodsRequest).let {
            ResponseEntity
                .status(HttpStatus.OK)
                .build()
        }
    }

    @DeleteMapping("/{goodsId}")
    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    fun deleteGoods(
        @PathVariable categoryId: Long,
        @PathVariable goodsId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit> {
        return goodsService.deleteGoods(userPrincipal.id, goodsId).let {
            ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
        }
    }

    @GetMapping
    fun getGoodsList(
        @PathVariable categoryId: Long,
    ): ResponseEntity<List<GoodsResponse>> {
        return goodsService.getGoodsList(categoryId).let {
            ResponseEntity
                .status(HttpStatus.OK)
                .body(it)
        }
    }

    @GetMapping("/{goodsId}")
    fun getGoods(
        @PathVariable categoryId: Long,
        @PathVariable goodsId: Long
    ): ResponseEntity<GoodsResponse> {
        return goodsService.getGoods(goodsId).let {
            ResponseEntity
                .status(HttpStatus.OK)
                .body(it)
        }
    }

}