package jp.fukicycle.jashter.api.controller

import jp.fukicycle.jashter.api.dto.request.LoginRequestDto
import jp.fukicycle.jashter.api.dto.response.LoginResponseDto
import jp.fukicycle.jashter.api.service.IAuthenticationService
import org.springframework.http.ResponseEntity
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
    fun login(@RequestBody loginRequestDto: LoginRequestDto): ResponseEntity<LoginResponseDto> {
        val result = authenticationService.login(loginRequestDto.username, loginRequestDto.password)
        val responseDto = LoginResponseDto(result)
        return if (result == null) ResponseEntity.badRequest().body(responseDto)
        else ResponseEntity.ok(responseDto)
    }

    @RequestMapping("/login")
    fun optionsLogin(): ResponseEntity<String> {
        return ResponseEntity.ok("OK")
    }

}