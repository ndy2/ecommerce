package com.example.user.service

import com.example.user.repository.UserRepository
import com.example.user.service.dto.CreateUserRequest
import com.example.user.service.dto.CreateUserResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun createUser(createUserRequest: CreateUserRequest): CreateUserResponse {
        val user = UserEntity(
            getNextUserId(),
            createUserRequest.name,
            createUserRequest.email,
            createUserRequest.pwd
        )

        user.encryptPwd()
        val savedUser = userRepository.save(user)

        return CreateUserResponse(
            savedUser.name,
            savedUser.email,
            savedUser.id!!
        )
    }

    private fun getNextUserId(): String {
        return java.util.UUID.randomUUID().toString()
    }
}