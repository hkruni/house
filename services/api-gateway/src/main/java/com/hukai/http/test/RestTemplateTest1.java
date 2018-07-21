package com.hukai.http.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest1 {
	
	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://www.baidu.com", String.class);
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
	}

}
