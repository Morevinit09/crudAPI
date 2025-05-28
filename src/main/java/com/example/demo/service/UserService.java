
package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Pagination.UserPagination;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;

public interface UserService {

	public String addUserInfo(UserDto userdto);

	public List<UserEntity> getAllUsers();

	// public UserEntity update(Long userId, UserEntity);

	public Void deleteUserById(Long userId);

	public String update(Long userId, UserDto userdto);

	public UserDto userFindById(Long userId);

	public List<?> findAllWithPagination(UserPagination pagination);

	public String generateExcelFile() throws IOException;

	public String saveDataFromExcelFile(MultipartFile file) throws IOException;

	String excelBatchProcessingbatchprocessing(MultipartFile file) throws IOException;

	void processQueuedFiles();

}
