package com.sumavision.tetris.spring.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.sumavision.tetris"})
@ServletComponentScan(basePackages = {"com.sumavision.tetris"})
@EnableFeignClients("com.sumavision.tetris")
@SpringBootApplication
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@EnableEurekaServer
public class TetrisSpringEurekaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TetrisSpringEurekaApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TetrisSpringEurekaApplication.class);
    }
	
}