package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.serviceimpl.UserServiceImpl;
import com.example.demoPaagination.UserPagination;
import com.example.demoresponse.Response;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl serviceImpl;      

	@PostMapping("/add")
	public Response putUser(@RequestBody UserDto userdto) {
		Response response = new Response();
		try {
			String userInfo = serviceImpl.addUserInfo(userdto);
			
			
			response.setData(userInfo);
			response.setMessage("succsess");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setStatus(true);
			response.setData(new ArrayList<>());
			response.setMessage(e.getMessage());
		} catch (Exception e) {                                                                                                                                
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(true);
			response.setMessage("Fail");
		}

		return response;
	}

	@PostMapping("/listing")
	public Response allUsers(@RequestBody UserPagination pagination) {
		Response response = new Response();
		try {
			List<UserEntity> allUsers = serviceImpl.findAllWithPagination(pagination);
			response.setData(allUsers);
			response.setStatus(true);
			response.setMessage("success");
			response.setTotalRecords(allUsers.size());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage("failed: " + e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	

	@GetMapping("/allUser")
	public Response getAllUsers() {
		Response response = new Response();
		try {
			List<UserEntity> allUsers = serviceImpl.getAllUsers();
			response.setData(allUsers);
			response.setStatus(true);
			response.setMessage("succsess");
			response.setTotalRecords(allUsers.size());

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(true);
			response.setData(new ArrayList<>());
			response.setMessage("Fail");
		}

		return response;

	}
	@GetMapping("/list/{userId}")
	public Response getResponse(@PathVariable Long userId) {
		Response response = new Response();
		try {
			UserDto dto = serviceImpl.userFindById(userId);
			response.setData(dto);
			response.setStatus(true);
			response.setMessage("succsess");
			
		}catch (Exception e) {
			response.setData(new ArrayList<>());
			response.setStatus(true);
			response.setMessage("succsess");
			
			// TODO: handle exception
		}
		return response;
	}

	@PatchMapping("/update/{userId}")
	public Response update(@PathVariable Long userId, @RequestBody UserDto userdto) {
		Response response = new Response();
		try {
			String update = serviceImpl.update(userId, userdto);
			response.setData(update);
			response.setStatus(true);
			response.setMessage("succsess");
			// response.setTotalRecords(allUsers.size());

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(true);
			response.setData(new ArrayList<>());
			response.setMessage("Fail");
		}

		return response;

	}

	@DeleteMapping("/delete/{userId}")
	public Response deleteUserById(@PathVariable Long userId) {
		Response response = new Response();
		try {
			String deleteUserById = serviceImpl.deleteUserById(userId);
			response.setData(deleteUserById);
			response.setStatus(true);
			response.setMessage("succsess");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(true);
			response.setMessage("Fail");
		}

		return response;
	}

	
	
	
	
}

