package jp.fukicycle.jashter.controller

import jp.fukicycle.jashter.model.User
import jp.fukicycle.jashter.service.IUserService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/user")
class UserController(
        private val userService: IUserService
) {
    @GetMapping("/get")
    fun get(authentication:Authentication): List<User> {
        println(authentication.principal as User)
        return userService.findAll()
    }
}