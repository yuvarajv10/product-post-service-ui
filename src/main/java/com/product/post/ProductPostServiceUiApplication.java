package com.product.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Product with Eureka client.
 * 
 * @author yuvaraj
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductPostServiceUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductPostServiceUiApplication.class, args);
	}

}
