package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.repsitory.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthenticationService : IAuthenticationService {
    @Autowired
    lateinit var userRepository: IUserRepository

    @Autowired
    lateinit var tokenService: ITokenService

    override fun login(username: String, password: String): String? {
        val loginUser = userRepository.findByUsernameAndPassword(username, password)
        return if (loginUser == null) null
        else tokenService.generate(loginUser)
    }
}