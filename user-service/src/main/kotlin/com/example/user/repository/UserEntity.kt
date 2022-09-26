package com.example.user.repository

import java.time.LocalDate
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "users")
class UserEntity(
    var userId: String,
    var name: String,
    var email: String,
    @Transient var pwd: String,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var createdDate: LocalDate = LocalDate.now()
    var encryptedPwd: String? = null

    fun encryptPwd(encoder: (CharSequence) -> String) {
        this.encryptedPwd = encoder(pwd)
    }
}