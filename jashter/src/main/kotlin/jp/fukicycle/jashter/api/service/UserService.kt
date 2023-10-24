package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.model.User
import jp.fukicycle.jashter.api.repsitory.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService : IUserService {
    @Autowired
    lateinit var userRepository: IUserRepository

    override fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow()
    }

    override fun findAll(): List<User> {
        return userRepository.findAll().toList()
    }
}