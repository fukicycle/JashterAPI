package jp.fukicycle.jashter.api.dto.response

data class UserResponseDto(
    val id: Long,
    val username: String,
    val firstName: String,
    val lastName: String,
    val icon: String,
    val nickname: String
)
