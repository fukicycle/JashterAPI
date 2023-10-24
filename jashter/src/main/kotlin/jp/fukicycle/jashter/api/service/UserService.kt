package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.response.UserResponseDto
import jp.fukicycle.jashter.api.model.User
import jp.fukicycle.jashter.api.repsitory.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService : IUserService {
    @Autowired
    lateinit var userRepository: IUserRepository

    override fun findById(id: Long): UserResponseDto {
        val user = userRepository.findById(id).orElseThrow()
        return UserResponseDto(user.id, user.username, user.firstName, user.lastName, user.icon, user.nickname)
    }

    override fun findAll(): List<UserResponseDto> {
        val items = mutableListOf<UserResponseDto>()
        val users = userRepository.findAll()
        for (user in users) {
            items.add(UserResponseDto(user.id, user.username, user.firstName, user.lastName, user.icon, user.nickname))
        }
        return items
    }
}