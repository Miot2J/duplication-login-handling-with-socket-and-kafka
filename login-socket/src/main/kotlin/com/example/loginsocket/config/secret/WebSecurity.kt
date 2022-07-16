package com.example.loginsocket.config.secret

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint

/*@Configuration
@EnableWebSecurity
class WebSecurity : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.cors().and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()

//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}*/

@Configuration
@EnableWebSecurity
class WebSecurity {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

        return http.build()
    }
}
