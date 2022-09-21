package com.example.secondservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {

	@GetMapping("/second-service/welcome")
	public String welcome(
		@RequestHeader(value = "second-req") String header
	) {
		log.info("header 가 잘 담겨 왔다 : {}", header.equals("second-req-val"));
		return "Welcome to fhe second service";
	}

}