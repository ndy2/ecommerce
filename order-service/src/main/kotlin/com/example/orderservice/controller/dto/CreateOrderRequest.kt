package com.example.orderservice.controller.dto

data class CreateOrderRequest(
    var quantity: Int,
    var unitPrice: Int,
    var productId: String,
)