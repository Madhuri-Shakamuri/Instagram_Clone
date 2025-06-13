package com.instagram.instagramclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.instagram.instagramclone.repository")
@EntityScan(basePackages = "com.instagram.instagramclone.model")
public class InstagramcloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramcloneApplication.class, args);
	}

}
