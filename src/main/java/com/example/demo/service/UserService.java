package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserEntity;

public interface UserService {

	public String addUserInfo(UserEntity userEntity);

	public List<UserEntity> getAllUsers();
	
	public	UserEntity update(Long userId, UserEntity uentity);
	
	public String deleteUserById(Long userId);
	









}
