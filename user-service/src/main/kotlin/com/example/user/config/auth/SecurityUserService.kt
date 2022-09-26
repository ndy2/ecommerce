package com.example.user.config.auth

import com.example.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityUserService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val userEntity = userRepository
            .findByEmail(email) ?: throw IllegalArgumentException("no such user email : $email")

        return SecurityUser(
            userEntity.id!!,
            userEntity.email,
            userEntity.encryptedPwd!!,
            enabled = true,
            accountNonExpired = true,
            credentialsNonExpired = true,
            accountNonLocked = true,
            authorities = mutableSetOf()
        )
    }
}