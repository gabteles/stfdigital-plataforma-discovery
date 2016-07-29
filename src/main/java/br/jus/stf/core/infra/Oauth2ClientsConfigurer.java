package br.jus.stf.core.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 29.07.2016
 */
@Configuration
@EnableOAuth2Client
public class Oauth2ClientsConfigurer {

	@Bean
	public RestTemplate restTemplate(OAuth2ClientContext oAuth2ClientContext) {
		return new OAuth2RestTemplate(new ClientCredentialsResourceDetails(), oAuth2ClientContext);
	}
	
}
