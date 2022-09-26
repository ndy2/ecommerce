package com.example.orderservice.controller.dto

import com.example.orderservice.jpa.OrderEntity
import java.time.LocalDate

data class OrderResponse(
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val unitPrice: Int,
    val totalPrice: Int,
    val createdDate: LocalDate,
) {

    companion object{

        fun ofEntity(entity: OrderEntity): OrderResponse {

            return OrderResponse(
                entity.orderId,
                entity.productId,
                entity.quantity,
                entity.unitPrice,
                entity.totalPrice,
                entity.createdDate,
            )
        }
    }
}
