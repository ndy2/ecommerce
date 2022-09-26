package com.example.user.config.auth

import com.example.user.service.dto.LoginRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(
    private val userDetailsService: UserDetailsService,
    private val env: Environment,
    authenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter(
    authenticationManager
) {

    private val objectMapper = ObjectMapper()

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        val loginRequest = objectMapper.readValue<LoginRequest>(request.inputStream)

        val token = UsernamePasswordAuthenticationToken(
            loginRequest.email,
            loginRequest.password,
            emptySet()
        )

        return authenticationManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val securityUser = authResult.principal as SecurityUser
        val email = securityUser.username
        println("login success email = $email")

        val jwt = Jwts.builder()
            .setSubject(securityUser.userId)
            .setExpiration(Date(System.currentTimeMillis() + env.getProperty("jwt.expiration")!!.toLong()))
            .setIssuer(env.getProperty("jwt.issuer"))
            .signWith(SignatureAlgorithm.HS512, env.getProperty("jwt.secret"))
            .compact()

        response.addHeader("token", jwt)
        response.addHeader("userId", securityUser.userId)
        response.writer.print("login success")
    }

}