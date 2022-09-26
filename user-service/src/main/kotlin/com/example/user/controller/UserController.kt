package com.example.user.controller

import com.example.user.PortNumberListner
import com.example.user.service.UserService
import com.example.user.service.dto.CreateUserRequest
import com.example.user.service.dto.CreateUserResponse
import com.example.user.service.dto.GetDetailedUserResponse
import com.example.user.service.dto.GetUsersResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService,
    private val portNumber: PortNumberListner,
    private val env: Environment
) {

    @GetMapping("/healthcheck")
    fun status(): String {
        return "ok port : ${portNumber.port}" +
                "jwt-expiraction : ${env.getProperty("jwt.expiration")}" +
                "jwt secret: ${env.getProperty("jwt.secret")}"
    }

    @PostMapping("/users")
    fun createUser(
        @RequestBody createUserRequest: CreateUserRequest
    ): CreateUserResponse {
        return userService.createUser(createUserRequest)
    }

    @GetMapping("/users/{userId}")
    fun getDetailedUser(
        @PathVariable userId: String
    ): GetDetailedUserResponse {
        return userService.getDetailedUserById(userId)
    }

    @GetMapping("/users")
    fun getUsers(): List<GetUsersResponse> {
        return userService.getAll()
    }

}