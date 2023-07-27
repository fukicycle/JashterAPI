package jp.fukicycle.jashter.service

import jp.fukicycle.jashter.model.User
import jp.fukicycle.jashter.repsitory.IUserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: IUserRepository
) : IUserService {
    override fun findById(id: Int): User {
        return userRepository.findById(id).orElseThrow()
    }
}