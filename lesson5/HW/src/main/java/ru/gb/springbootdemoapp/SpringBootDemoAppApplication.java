package ru.gb.springbootdemoapp;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootDemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoAppApplication.class, args);
	}

	@Bean
	public SessionFactory sessionFactoryBean() {
		return new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();
	}
}
