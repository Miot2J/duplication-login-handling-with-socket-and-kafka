package com.example.loginsocket.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApi(
    @Value("\${open-api.servers:/}")
    val servers: List<String>,
) {
    @Bean
    fun customOpenAPI(): OpenAPI? {
        return OpenAPI().addServersItem(Server().url("/"))
            .info(
                Info()
                    .title("Socket API")
                    .version("1.0.0")
                    .termsOfService("https://swagger.io/terms/")
                    .license(License().name("Apache 2.0").url("https://springdoc.org"))
            )
            .servers(servers.map { Server().url(it) })
    }
}
