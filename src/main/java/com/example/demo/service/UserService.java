package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demoPaagination.CustomResponse;
import com.example.demoPaagination.UserPagination;

public interface UserService {

	public String addUserInfo(UserDto  userdto);

	public List<UserEntity> getAllUsers();
	
	//public	UserEntity update(Long userId, UserEntity);
	
	public String deleteUserById(Long userId);

	public String update(Long userId, UserDto userdto);
	
	public UserDto userFindById(Long userId) ;

	List<?> findAllWithPagination(UserPagination pagination);








}
