package jp.fukicycle.jashter.config

import jp.fukicycle.jashter.service.ITokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val tokenService: ITokenService,
) {
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
                .csrf { csrf: CsrfConfigurer<HttpSecurity> -> csrf.disable() }
                .authorizeRequests { auth ->
                    auth.requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                            .anyRequest().authenticated()
                }
                .oauth2ResourceServer { obj: OAuth2ResourceServerConfigurer<HttpSecurity?> -> obj.jwt {} }
                .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
                .httpBasic(withDefaults())
                .headers { headers ->
                    headers.frameOptions { frameOptions -> frameOptions.disable() }
                            .xssProtection { xssProtection -> xssProtection.disable() }
                }
                .authenticationManager { authenticationManager ->
                    val jwt = authenticationManager as BearerTokenAuthenticationToken
                    val user = tokenService.parse(jwt.token) ?: throw InvalidBearerTokenException("Invalid token")
                    UsernamePasswordAuthenticationToken(user, "", listOf(SimpleGrantedAuthority("USER")))
                }
                .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8080")
        configuration.allowedMethods = listOf("GET", "POST", "PUT")
        configuration.allowedHeaders = listOf("Authorization", "content-type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}