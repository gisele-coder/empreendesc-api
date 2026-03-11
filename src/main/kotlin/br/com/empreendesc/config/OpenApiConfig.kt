package br.com.empreendesc.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI =
        OpenAPI().info(
            Info()
                .title("Empreendimentos SC API")
                .description("REST API for managing business entities in Santa Catarina")
                .version("1.0")
        )
}

