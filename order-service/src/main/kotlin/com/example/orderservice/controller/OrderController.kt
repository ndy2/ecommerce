package com.example.orderservice.controller

import com.example.orderservice.controller.dto.CreateOrderRequest
import com.example.orderservice.controller.dto.OrderResponse
import com.example.orderservice.jpa.OrderDao
import com.example.orderservice.jpa.OrderEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class OrderController(
    private val orderDao: OrderDao
) {

    @PostMapping("/{userId}/orders")
    fun createOrder(
        @PathVariable userId: String,
        @RequestBody request: CreateOrderRequest,
    ): OrderResponse {
        println("create order request of userID : ${userId}, request : ${request}")
        val orderId = UUID.randomUUID().toString()
        val totalPrice = request.unitPrice * request.quantity

        return OrderResponse.ofEntity(
            orderDao.save(
                OrderEntity(
                    orderId,
                    request.quantity,
                    request.unitPrice,
                    totalPrice,
                    request.productId,
                    userId
                )
            )
        )
    }

    @GetMapping("/orders/{orderId}")
    fun getByOrderId(@PathVariable orderId: String): OrderResponse {

        return OrderResponse.ofEntity(orderDao.findByOrderId(orderId))
    }

    @GetMapping("/{userId}/orders")
    fun getByUserId(@PathVariable userId: String): List<OrderResponse> {
        return orderDao.findByUserId(userId)
            .map { entity -> OrderResponse.ofEntity(entity) }
            .toList()
    }

}