package com.example.user.controller

import com.example.user.service.UserService
import com.example.user.service.dto.CreateUserRequest
import com.example.user.service.dto.CreateUserResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/")
@RestController
class UserController(
    private val userService: UserService
) {

    @Value("\${greeting.message}")
    lateinit var greetingMessage: String;

    @GetMapping("/healthcheck")
    fun status(): String {
        return "ok"
    }

    @GetMapping("welcome")
    fun welcome(): String {
        return greetingMessage
    }

    @PostMapping("/users")
    fun createUser(
        @RequestBody createUserRequest: CreateUserRequest
    ): CreateUserResponse {
        return userService.createUser(createUserRequest)
    }

}