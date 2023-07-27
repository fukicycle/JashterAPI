package jp.fukicycle.jashter.config

import jp.fukicycle.jashter.service.ITokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
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
class AuthorizationConfig(
        private val tokenService: ITokenService,
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorizeHttpRequests ->
            authorizeHttpRequests
                    .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                    .requestMatchers("/api/**").authenticated()
                    .anyRequest().permitAll()
        }
        http.oauth2ResourceServer { oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()) }
        http.authenticationManager { authenticationManager ->
            val jwt = authenticationManager as BearerTokenAuthenticationToken
            val user = tokenService.parse(jwt.token) ?: throw InvalidBearerTokenException("Invalid token")
            UsernamePasswordAuthenticationToken(user, "", listOf(SimpleGrantedAuthority("USER")))
        }

        http.cors(Customizer.withDefaults())
        http.sessionManagement { sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        http.csrf { csrf ->
            csrf.disable()
        }
        http.headers { headers ->
            headers.frameOptions { frameOptions ->
                frameOptions.disable()
            }.xssProtection { xssProtection ->
                xssProtection.disable()
            }
        }
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8080")
        configuration.allowedMethods = listOf("GET", "POST", "PUT")
        configuration.allowedHeaders = listOf("authorization", "content-type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}