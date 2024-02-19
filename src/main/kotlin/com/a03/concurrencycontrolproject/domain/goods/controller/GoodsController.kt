package com.a03.concurrencycontrolproject.domain.goods.controller

import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.goods.dto.CreateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.GoodsResponse
import com.a03.concurrencycontrolproject.domain.goods.dto.SelectGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.UpdateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.service.GoodsService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/categories/{categoryId}/goods")
class GoodsController(
    private val goodsService: GoodsService
) {

    @Operation(summary = "상품 생성")
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

    @Operation(summary = "상품 수정")
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

    @Operation(summary = "상품 삭제")
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

    @Operation(summary = "상품 목록 조회")
    @GetMapping
    fun getGoodsList(
        @PathVariable categoryId: Long,
        @RequestParam title: String?,
        @RequestParam place: String?,
        @PageableDefault(size = 15, sort = ["id"]) pageable: Pageable,
    ): ResponseEntity<Page<GoodsResponse>> {
        return goodsService.getGoodsList(
            pageable,
            SelectGoodsRequest(
                categoryId, title, place
            )
        ).let {
            ResponseEntity
                .status(HttpStatus.OK)
                .body(it)
        }
    }

    @Operation(summary = "상품 조회")
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