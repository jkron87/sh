package com.spothero.kron

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.google.common.base.Predicate
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.PathSelectors.regex
import com.google.common.base.Predicates.or
import springfox.documentation.service.Contact


@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun postsApi(): Docket? {
        return Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build()
    }

    private fun postPaths(): Predicate<String?>? {
        return or(regex("/api/price"), regex("/api/rates"))
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder().title("SpotHero Take-home API")
                .description("Api that gets parking price for a time range")
                .contact(Contact("Jennifer Kron", "https://github.com/jkron87", "jenniferkron@gmail.com"))
                .build()
    }
}