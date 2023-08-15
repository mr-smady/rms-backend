package com.coderz.graphql.service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {
    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity) =
        http {
            cors {   }
            csrf { disable() }
            authorizeExchange {
                authorize(anyExchange, permitAll)
                authorize("/**", permitAll)
                authorize(
                    PathPatternParserServerWebExchangeMatcher(
                        "/**",
                        HttpMethod.OPTIONS
                    ), permitAll
                )
            }
        }
}



