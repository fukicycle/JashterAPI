package jp.fukicycle.jashter.api.config

import jp.fukicycle.jashter.api.service.ITokenService
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
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


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
                    auth.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
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
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5058","https://fukicycle.github.io/")
                        .allowedMethods("GET", "POST", "PUT", "OPTIONS")
                        .allowedHeaders("Authorization", "content-type")
                        .allowCredentials(true)
            }
        }
    }
}