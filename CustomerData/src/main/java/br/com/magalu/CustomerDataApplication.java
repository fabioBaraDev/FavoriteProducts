package br.com.magalu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CustomerDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDataApplication.class, args);
	}

}
