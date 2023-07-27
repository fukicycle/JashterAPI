package jp.fukicycle.jashter.api.controller

import jp.fukicycle.jashter.api.dto.LoginDto
import jp.fukicycle.jashter.api.dto.LoginResponseDto
import jp.fukicycle.jashter.api.service.AuthenticationService
import jp.fukicycle.jashter.api.service.IAuthenticationService
import jp.fukicycle.jashter.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthenticationController(
        private val authenticationService: IAuthenticationService
) {

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): LoginResponseDto {
        val result = authenticationService.login(loginDto.username, loginDto.password)
        return if (result == null) LoginResponseDto(null, HttpStatus.BAD_REQUEST)
        else LoginResponseDto(result, HttpStatus.OK)
    }

}