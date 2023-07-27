package jp.fukicycle.jashter.dto

import org.springframework.http.HttpStatus

data class LoginResponseDto(
        val token: String?,
        val statusCode: HttpStatus
)
