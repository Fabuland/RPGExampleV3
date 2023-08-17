package com.fabu.rpgexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.fabu.rpgexample"})
@EnableJpaRepositories(basePackages="com.fabu.rpgexample.repository")
@EnableTransactionManagement
@EntityScan(basePackages="com.fabu.rpgexample.model")
public class RpgexampleApplication {

	public static void main(String[] args) {
		System.out.println("Application starting...");
		SpringApplication.run(RpgexampleApplication.class, args);
		System.out.println("Application started!");
	}

}
