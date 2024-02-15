package com.a03.concurrencycontrolproject.domain.goods.controller

import com.a03.concurrencycontrolproject.domain.goods.dto.CreateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.GoodsResponse
import com.a03.concurrencycontrolproject.domain.goods.dto.UpdateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.service.GoodsService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/categories/{categoryId}/goods")
class GoodsController(
    val goodsService: GoodsService
) {

    // TODO PreAuthorize hasRole
    @PostMapping
    fun createGoods(
        @PathVariable categoryId: Long,
        @Valid @RequestBody createGoodsRequest: CreateGoodsRequest
    ): ResponseEntity<Unit> {
        // TODO user dependency
        val userId = 1L
        createGoodsRequest.categoryId = categoryId
        createGoodsRequest.userId = userId
        goodsService.createGoods(createGoodsRequest)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build()
    }

    @PutMapping("/{goodsId}")
    fun updateGoods(
        @PathVariable categoryId: Long,
        @PathVariable goodsId: Long,
        @RequestBody updateGoodsRequest: UpdateGoodsRequest
    ): ResponseEntity<Unit> {
        updateGoodsRequest.goodsId = goodsId
        // TODO userId get
        updateGoodsRequest.userId = 0L
        goodsService.updateGoods(updateGoodsRequest)
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

    @DeleteMapping("/{goodsId}")
    fun deleteGoods(
        @PathVariable categoryId: Long,
        @PathVariable goodsId: Long
    ): ResponseEntity<Unit> {
        goodsService.deleteGoods(goodsId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @GetMapping
    fun getGoodsList(
        @PathVariable categoryId: Long,
    ): ResponseEntity<List<GoodsResponse>> {
        val goodsResponseList = goodsService.getGoodsList(categoryId)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(goodsResponseList)
    }

    @GetMapping("/{goodsId}")
    fun getGoods(
        @PathVariable categoryId: Long,
        @PathVariable goodsId: Long
    ): ResponseEntity<GoodsResponse> {
        val goodsResponse = goodsService.getGoods(goodsId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(goodsResponse)
    }

}