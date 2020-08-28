package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.persistence.DynamoDB.repositories.ProductInfoRepository;

@SpringBootApplication
@EnableJpaRepositories(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                ProductInfoRepository.class})})
public class RestWithSwagApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWithSwagApplication.class, args);
	}
}
