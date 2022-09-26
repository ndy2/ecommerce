package com.example.user.service.dto

import com.example.NoArgs

@NoArgs
data class LoginRequest(
    val email: String,
    val password: String,
)
