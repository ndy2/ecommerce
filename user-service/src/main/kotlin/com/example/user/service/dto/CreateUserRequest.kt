package com.example.user.service.dto

data class CreateUserRequest(
    val email: String,
    val pwd: String,
    val name: String,
)
