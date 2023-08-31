package jp.fukicycle.jashter.api

import jp.fukicycle.jashter.api.controller.AuthenticationController
import jp.fukicycle.jashter.api.controller.UserController
import jp.fukicycle.jashter.api.service.IAuthenticationService
import jp.fukicycle.jashter.api.service.ITokenService
import jp.fukicycle.jashter.api.service.IUserService
import jp.fukicycle.jashter.api.service.UserService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
class JashterApplicationTests {
    @Autowired
    lateinit var authenticationService: IAuthenticationService

    @Autowired
    lateinit var tokenService: ITokenService
    @Test
    fun loginIsFailed() {
        assert(authenticationService.login("TEST", "TEST") == null)
    }

    @Test
    fun loginIsSuccess() {
        assert(authenticationService.login("user1", "pass") != null)
    }

    @Test
    fun tokenIsValid() {
        val token = authenticationService.login("user1", "pass")
        if (token != null) {
            val user = tokenService.parse(token)
            assert(user != null)
            assert(user!!.username == "user1")
        } else {
            fail(message = "token is null")
        }
    }
}
