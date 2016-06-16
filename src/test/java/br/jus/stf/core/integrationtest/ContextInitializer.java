package br.jus.stf.core.integrationtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 21.12.2015
 */
@SpringBootApplication
@ComponentScan("br.jus.stf")
public class ContextInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ContextInitializer.class, args);
	}
	
}
