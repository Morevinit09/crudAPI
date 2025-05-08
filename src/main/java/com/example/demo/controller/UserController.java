package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	private UserServiceImpl serviceImpl;

	@PostMapping("/add")
	public String putUser(@RequestBody UserEntity userEntity) {
		String userInfo = serviceImpl.addUserInfo(userEntity);
		return userInfo;
	}

	@GetMapping("/allUsers")
	public List<UserEntity> getAllUsers() {
		return serviceImpl.getAllUsers();

	}

	@PutMapping("/update/{userId}")
	public UserEntity update(@PathVariable Long userId, @RequestBody UserEntity userEntity) {
		return serviceImpl.update(userId, userEntity);
	}

	@DeleteMapping("/delete/{userId}")
	public String deleteUserById(@PathVariable Long userId) {
		serviceImpl.deleteUserById(userId);
		return "Success";
	}

}
