package jp.fukicycle.jashter.dto

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

data class LoginResponseDto(
        val token: String?,
        val statusCode: HttpStatusCode
)
