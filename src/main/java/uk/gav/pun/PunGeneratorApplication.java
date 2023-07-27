package uk.gav.pun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "uk.gav.pun.repository" })
public class PunGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PunGeneratorApplication.class, args);
	}

}
