package net.rainmore

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}

object Application extends App {
    SpringApplication.run(classOf[Application], args: _*)
}

@Configuration
@EnableAutoConfiguration
@ComponentScan(value = Array("net.rainmore"))
class Application {}