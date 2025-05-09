package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;

public interface UserService {

	public String addUserInfo(UserDto  userdto);

	public List<UserEntity> getAllUsers();
	
	//public	UserEntity update(Long userId, UserEntity);
	
	public String deleteUserById(Long userId);

	public UserEntity update(Long userId, UserDto userDto);
	









}
