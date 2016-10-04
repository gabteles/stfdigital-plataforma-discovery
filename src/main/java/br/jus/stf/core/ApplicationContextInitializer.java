package br.jus.stf.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * Classe para incialização da aplicação.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 05.04.2016
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@EnableEurekaServer
@EnableConfigServer
@EnableAdminServer
@EnableCircuitBreaker
@EnableTurbine
@EnableHystrixDashboard
public class ApplicationContextInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationContextInitializer.class, args);
	}
	
}
