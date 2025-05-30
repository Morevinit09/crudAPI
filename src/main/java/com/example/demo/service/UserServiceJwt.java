
package com.example.demo.service;

import com.example.demo.dto.AuthenticationRequest;

public interface UserServiceJwt {
	public String register(AuthenticationRequest authenticationRequest);
	
	public String login(String userName, String userPassword);
}
