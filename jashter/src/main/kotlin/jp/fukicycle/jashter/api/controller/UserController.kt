package jp.fukicycle.jashter.api.controller

import jp.fukicycle.jashter.api.model.User
import jp.fukicycle.jashter.api.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/user")
class UserController{
    @Autowired
    lateinit var userService: IUserService

    @GetMapping("")
    fun get(authentication: Authentication): ResponseEntity<User> {
        val user = authentication.principal as User
        return ResponseEntity.ok(userService.findById(user.id))
    }
}