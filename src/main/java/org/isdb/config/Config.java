package com.isdb.config;

@Configuration
public class Config {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().registerModule(new JavaTimeModule());
	}
}
