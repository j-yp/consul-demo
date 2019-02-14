package com.example.consul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.consul.config.StudentConfig;
import com.example.consul.entity.User;
import com.example.consul.service.UserService;

@RestController
@RequestMapping("consul")
public class TestController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private StudentConfig studentConfig;
	
	@Value("${myName}")
	private String myName;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("test1")
	public String testDemo() {
		String result = restTemplate.getForObject("http://consul-provider/provider/test", String.class);
		return result;
	}
	
	@GetMapping("test2")
	public String testDemo2() {
		List<ServiceInstance> instances = discoveryClient.getInstances("consul-provider");
		for (ServiceInstance serviceInstance : instances) {
			System.out.println(serviceInstance.getUri());
		}
		return "test2";
	}
	
	@GetMapping("test3")
	public String testConfig() {
		System.out.println(studentConfig);
		System.out.println("myName: " + myName);
		return "test3";
	}
	
	@GetMapping("test4")
	public String testDataSourceChange() {
		User user = userService.getUserById("0002302f74ae49328d04a994b85df50d");
		System.out.println(user.getName());
		return "test4";
	}
}
