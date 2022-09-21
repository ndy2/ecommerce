package com.example.firstservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {

	@GetMapping("/first-service/welcome")
	public String welcome(
		@RequestHeader(value = "first-req") String header
	) {
		log.info("header 가 잘 담겨 왔다 : {}", header.equals("first-req-val"));
		return "Welcome to fhe first service";
	}

}