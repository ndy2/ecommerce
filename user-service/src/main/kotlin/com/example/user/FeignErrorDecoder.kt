package com.example.user

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception

class FeignErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {

        if (response.status() == 404) {
            if (methodKey.contains("getOrders")) {
                return ResponseStatusException(
                    HttpStatus.valueOf(response.status()),
                    "User's order is not found"
                )
            }
        }

        return Exception(response.reason())
    }
}