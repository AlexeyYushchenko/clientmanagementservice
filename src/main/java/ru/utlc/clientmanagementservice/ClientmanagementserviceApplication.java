package ru.utlc.clientmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ClientmanagementserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientmanagementserviceApplication.class, args);
	}

}
