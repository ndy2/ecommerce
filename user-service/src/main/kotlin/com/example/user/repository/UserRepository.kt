package com.example.user.repository

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, String> {
    fun findByEmail(email: String): UserEntity?
}