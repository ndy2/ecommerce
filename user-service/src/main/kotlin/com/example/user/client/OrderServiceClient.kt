package com.example.user.client

import com.example.user.service.dto.GetDetailedUserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "order-service")
interface OrderServiceClient {

    @GetMapping("/{userId}/orders")
    fun getOrders(@PathVariable userId: String) : List<GetDetailedUserResponse.OrderResponse>
}