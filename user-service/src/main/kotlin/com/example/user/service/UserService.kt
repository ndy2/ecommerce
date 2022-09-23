package com.example.user.service

import com.example.user.repository.UserEntity
import com.example.user.repository.UserRepository
import com.example.user.service.dto.CreateUserRequest
import com.example.user.service.dto.CreateUserResponse
import com.example.user.service.dto.GetDetailedUserResponse
import com.example.user.service.dto.GetUsersResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun createUser(createUserRequest: CreateUserRequest): CreateUserResponse {
        val user = UserEntity(
            getNextUserId(),
            createUserRequest.name,
            createUserRequest.email,
            createUserRequest.pwd
        )

        user.encryptPwd(passwordEncoder::encode)
        val savedUser = userRepository.save(user)

        return CreateUserResponse(
            savedUser.name,
            savedUser.email,
            savedUser.id!!
        )
    }

    @Transactional(readOnly = true)
    fun getDetailedUserById(userId: String): GetDetailedUserResponse {
        val user = userRepository.findById(userId).orElseThrow()

        return GetDetailedUserResponse(
            user.name,
            user.email,
            user.id!!,
            listOf(
                GetDetailedUserResponse.OrderResponse(
                    "productId",
                    1,
                    1,
                    1,
                    LocalDate.now(),
                    "orderId"
                )
            )
        )
    }

    @Transactional(readOnly = true)
    fun getAll(): List<GetUsersResponse> {
        return userRepository.findAll().map { user ->
            GetUsersResponse(
                user.name,
                user.email,
                user.id!!
            )
        }
    }


    private fun getNextUserId(): String {
        return java.util.UUID.randomUUID().toString()
    }
}