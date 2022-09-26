package com.example.user.service.dto

import java.time.LocalDate

data class GetDetailedUserResponse(
    val name: String,
    val email: String,
    val userId: String,
    val orders: List<OrderResponse>
) {

    data class OrderResponse(
        val orderId: String,
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val totalPrice: Int,
        val createdDate: LocalDate,
    )
}