package com.springframework.samples.petclinic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
@ComponentScan("com.springframework.samples.petclinic.vet")
@EntityScan("com.springframework.samples.petclinic.vet")
@RestController
public class VetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetServiceApplication.class, args);
	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "OPTIONS", "POST", "PUT", "DELETE");
	}

	@RequestMapping("/")
	public ResponseEntity rootContext() {
		return ResponseEntity.ok("[VetService] Succeeded...");
	}
}
