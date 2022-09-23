package com.example.user.config.auth

import com.example.user.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityUserService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val userEntity = userRepository.findByEmail(email)

        return User(
            userEntity.email,
            userEntity.encryptedPwd,
            true,
            true,
            true,
            true,
            emptySet()
        )
    }
}