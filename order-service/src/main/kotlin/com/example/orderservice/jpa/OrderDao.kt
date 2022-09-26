package com.example.orderservice.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface OrderDao : JpaRepository<OrderEntity, Long>{

    fun findByOrderId(orderId: String) : OrderEntity
    fun findByUserId(userId: String) : List<OrderEntity>
}