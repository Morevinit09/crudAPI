package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.response.Response;
import com.example.demo.service.UserServiceJwt;


@RestController
@RequestMapping("/auth")
public class UserjwtController {
	
	
	@Autowired
	private UserServiceJwt usersService;
	
	@PostMapping("/register")
	public Response register(@RequestBody AuthenticationRequest request ) {
		Response response = new Response();

		try {
			String register = usersService.register(request);
			
			response.setStatus(true);
			response.setMessage("success");
			response.setData(register);
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage("Failed" + e.getMessage());
			response.setData(null);
		}
		return response;

	}
	
	@PostMapping("/login")
	public  Response login(@RequestParam String username,@RequestParam String password) {

		Response response = new Response();

		try {
			String login = usersService.login(username, password);
			response.setStatus(true);
			response.setMessage(" successfully");
			response.setData(login);

		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMessage("Failed" + e.getMessage());
			response.setData(null);
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage("Failed" + e.getMessage());
			response.setData(null);
		}

		return response;

	}

}