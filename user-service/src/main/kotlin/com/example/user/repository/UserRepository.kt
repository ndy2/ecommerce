package com.example.user.repository

import com.example.user.service.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, String>