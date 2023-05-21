package com.toks.api.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebSession
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.schema.AlternateTypeRules
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Swagger Config
 *
 * @see <a href="http://localhost:8080/swagger-ui.html">Swagger</a>
 **/
@Configuration
@EnableSwagger2WebMvc
class SwaggerConfig {
    @Bean
    fun docket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .alternateTypeRules(
                AlternateTypeRules.newRule(
                    Pageable::class.java,
                    SwaggerPageable::class.java,
                    0
                )
            )
            .enable(true)
            .useDefaultResponseMessages(false)
            .ignoredParameterTypes(
                WebSession::class.java,
                HttpServletRequest::class.java,
                HttpServletResponse::class.java,
                ServerHttpRequest::class.java,
                ServerHttpResponse::class.java,
                ServerWebExchange::class.java
            )
            .apiInfo(apiInfo())
            .genericModelSubstitutes(
                Optional::class.java,
                ResponseEntity::class.java
            )
            .select()
            .paths(PathSelectors.regex("/api/.*"))
            .build()
    }

    private fun apiInfo() = ApiInfoBuilder()
        .title("Toks V2 API")
        .version("v1.0.0")
        .build()

    private data class SwaggerPageable(
        val page: Int,
        val size: Int,
        val sort: List<String>
    )
}
