package com.example.demo.controller;

import java.util.ArrayList;
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

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.serviceimpl.UserServiceImpl;
import com.example.demoresponse.Response;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl serviceImpl;

	@PostMapping("/add")
	public Response putUser(@RequestBody UserDto  userdto) {
		Response response = new Response();
		try {
			String userInfo = serviceImpl.addUserInfo(userdto);
			response.setData(userInfo);
			response.setMessage("succsess");

		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setMessage("Fail");
		}

		return response;
	}

	@GetMapping("/allUser")
	public Response getAllUsers() {
		Response response = new Response();
		try {
			List<UserEntity> allUsers = serviceImpl.getAllUsers();
			response.setData(allUsers);
			response.setMessage("succsess");
			response.setTotalRecords(allUsers.size());

		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setMessage("Fail");
		}

		return response;

	}

	@PutMapping("/update/{userId}")
	public UserEntity update(@PathVariable Long userId, @RequestBody UserDto userDto) {
		return serviceImpl.update(userId, userDto);
	}

	@DeleteMapping("/delete/{userId}")
	public Response deleteUserById(@PathVariable Long userId) {
		Response response = new Response();
		try {
			String deleteUserById = serviceImpl.deleteUserById(userId);
			response.setData(deleteUserById);
			response.setMessage("succsess");

		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setMessage("Fail");
		}

		return response;
	}

}
