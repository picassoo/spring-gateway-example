package com.gateway.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/employee")
public class SecondController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@GetMapping(value = "/microservice1")
	public String method1() {
		LOG.info("Inside method1");
		String baseUrl = "http://localhost:8081/microservice2";
		String response = (String) restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
		LOG.info("The response received by method1 is " + response);
		return response;
	}
	
	@GetMapping("/message")
	public String test() {
		return "Hello JavaInUse Called in First Service";
	}

}