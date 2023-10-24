package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.response.UserResponseDto
import jp.fukicycle.jashter.api.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenService : ITokenService {
    @Autowired
    lateinit var jwtDecoder: JwtDecoder

    @Autowired
    lateinit var jwtEncoder: JwtEncoder

    @Autowired
    lateinit var userService: IUserService

    override fun generate(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30, ChronoUnit.DAYS))
            .subject(user.username)
            .claim("userId", user.id)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    override fun parse(token: String): UserResponseDto? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as Long
            userService.findById(userId)
        } catch (e: Exception) {
            null
        }
    }
}