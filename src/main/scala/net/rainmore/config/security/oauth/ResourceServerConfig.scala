package net.rainmore.config.security.oauth

import javax.inject.Inject

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.config.annotation.web.configuration.{EnableResourceServer, ResourceServerConfigurerAdapter}
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.{DefaultTokenServices, ResourceServerTokenServices}


@Configuration
@EnableResourceServer
class ResourceServerConfig @Inject
(
    remoteTokeService: RemoteTokeService
) extends ResourceServerConfigurerAdapter {

    /**
      * The project is a pure OAuth2 Resource server as such there is no need of an authentication manager
      * But if an authentication manager is not defined then Spring Security creates a default authentication manager
      * In order to prevent that from happening we define an noop authentication manager here
      * @see http://forum.spring.io/forum/spring-projects/security/oauth/114319-separate-resource-server-and-authorization-server
      * @return AuthenticationManager
      */
    @Bean
    def createAuthenticationManager(): AuthenticationManager = {
        new AuthenticationManager {
            override def authenticate(authentication: Authentication): Authentication = {
                throw new UnsupportedOperationException("No authentication should be done with this AuthenticationManager")
            }
        }
    }

    override def configure(resources: ResourceServerSecurityConfigurer): Unit = {
        resources.tokenServices(remoteTokeService)
    }

    override def configure(http: HttpSecurity): Unit = {
        // TODO to implement OAuth2 authentication server
//        http.authorizeRequests()
//            .anyRequest()
//            .access("#oauth2.hasScope('restApi')")

        http.authorizeRequests()
            .antMatchers("/**").permitAll
    }

}
