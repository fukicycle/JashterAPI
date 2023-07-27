package jp.fukicycle.jashter.service

import jp.fukicycle.jashter.repsitory.IUserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
        private val userRepository: IUserRepository,
        private val tokenService: ITokenService
) : IAuthenticationService {
    override fun login(username: String, password: String): String? {
        val loginUser = userRepository.findByUsernameAndPassword(username, password)
        return if (loginUser == null) null
        else tokenService.generate(loginUser)
    }
}