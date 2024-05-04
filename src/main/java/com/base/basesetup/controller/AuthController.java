package com.base.basesetup.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@CrossOrigin  
public class AuthController {

	@GetMapping("/test")
	public String test() {
		return "Hello";
	}
	
	
}
