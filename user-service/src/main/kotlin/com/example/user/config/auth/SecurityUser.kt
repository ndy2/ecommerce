package com.example.user.config.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class SecurityUser(
    val userId: String,
    username: String,
    password: String,
    enabled: Boolean,
    accountNonExpired: Boolean,
    credentialsNonExpired: Boolean,
    accountNonLocked: Boolean,
    authorities: MutableCollection<out GrantedAuthority>
)
    : User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {
}