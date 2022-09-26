package com.example.user.repository

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Id var id: String? = null,
    var name: String,
    var email: String,
    @Transient var pwd: String,
) {
    var createdDate: LocalDate = LocalDate.now()
    var encryptedPwd: String? = null

    fun encryptPwd(encoder: (CharSequence) -> String) {
        this.encryptedPwd = encoder(pwd)
    }
}