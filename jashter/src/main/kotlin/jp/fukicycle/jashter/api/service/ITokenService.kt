package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.response.UserResponseDto
import jp.fukicycle.jashter.api.model.User

interface ITokenService {
    fun generate(user: User): String
    fun parse(token: String): UserResponseDto?
}