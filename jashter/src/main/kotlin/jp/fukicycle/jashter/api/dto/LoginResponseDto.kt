package jp.fukicycle.jashter.api.dto

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

data class LoginResponseDto(
        val token: String?
)
