package org.gotson.komga.infrastructure.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.data.domain.Pageable
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.context.request.WebRequest
import springfox.documentation.schema.AlternateTypeRules
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

  @Bean
  fun getDocket(): Docket =
    Docket(DocumentationType.SWAGGER_2)
      .ignoredParameterTypes(
        AuthenticationPrincipal::class.java,
        WebRequest::class.java
      )
      .alternateTypeRules(AlternateTypeRules.newRule(
        Pageable::class.java,
        PageableMixin::class.java,
        Ordered.HIGHEST_PRECEDENCE
      ))

  private class PageableMixin {
    val page = 0
    val size = 20
    val sort = ""
  }
}
