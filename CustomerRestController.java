package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerRestController {
	
	@Autowired
	private CustomerService service;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/register")
	public Customer saveCustomer(@RequestBody Customer c) {
		return service.saveCustomer(c);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Customer c) {
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(c.getEmail(),c.getPwd());
		Authentication authenticate=authManager.authenticate(token);
		boolean status= authenticate.isAuthenticated();
		if(status) {
			return new ResponseEntity<String>("WELCOME",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("FAILED",HttpStatus.BAD_REQUEST);
		}
	}

}
