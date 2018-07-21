package com.mooc.house.hsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient

//Swagger暴露接口文档   访问http://locallhost:8084/swagger-ui.html可以在线查看接口
@EnableSwagger2  
public class HouseSrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseSrvApplication.class, args);
	}
}
