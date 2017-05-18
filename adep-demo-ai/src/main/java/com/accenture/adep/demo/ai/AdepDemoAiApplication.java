package com.accenture.adep.demo.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

//@EnableFeignClients
//@EnableEurekaClient
@ComponentScan
@SpringBootApplication
public class AdepDemoAiApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
	public static void main(String[] args) {
		SpringApplication.run(AdepDemoAiApplication.class, args);
	}
}
