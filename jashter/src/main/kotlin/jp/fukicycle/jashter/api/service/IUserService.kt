package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.response.UserResponseDto
import jp.fukicycle.jashter.api.model.User

interface IUserService {
    fun findById(id: Long): UserResponseDto
    fun findAll(): List<UserResponseDto>
}