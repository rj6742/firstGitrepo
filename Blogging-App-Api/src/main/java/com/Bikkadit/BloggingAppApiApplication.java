package com.Bikkadit;


import javax.persistence.Basic;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelmapper()
	{
		return new ModelMapper();
	}

}
