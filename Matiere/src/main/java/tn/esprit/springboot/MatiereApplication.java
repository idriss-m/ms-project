package tn.esprit.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MatiereApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatiereApplication.class, args);
	}

}
