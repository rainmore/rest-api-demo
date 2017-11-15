package net.rainmore.config.swagger

import java.util

import org.springframework.context.annotation.{Bean, Configuration}
import springfox.documentation.builders.{PathSelectors, RequestHandlerSelectors}
import springfox.documentation.service.{ApiInfo, Contact, VendorExtension}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

object Config {

    private val apiInfo: ApiInfo = new ApiInfo(
        "REST API",
        "REST API Description",
        "1.0",
        "urn:tos",
        new Contact("Jie Rong", "http://rainmore.net/", "rainmore24@gmail.com"),
        "Apache License Version 2.0",
        "https://www.apache.org/licenses/LICENSE-2.0",
        new util.ArrayList[VendorExtension[_]]
    )
}

@Configuration("swaggerConfiguration")
@EnableSwagger2
class Config {

    @Bean
    def productApi: Docket = {
        new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("net.rainmore.controllers"))
            .paths(PathSelectors.regex("/api/.*"))
            .build()
            .apiInfo(Config.apiInfo)
    }

}
