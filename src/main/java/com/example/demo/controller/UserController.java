package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Pagination.UserPagination;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.response.Response;
import com.example.demo.serviceimpl.UserServiceImpl;

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
			List<?> allUsers = serviceImpl.findAllWithPagination(pagination);
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

		} catch (Exception e) {
			response.setData(new ArrayList<>());
			response.setMessage("succsess");
			response.setStatus(false);
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
	public void deleteUserById(@PathVariable Long userId) {
		Response response = new Response();
		try {
			Void deleteUserById = serviceImpl.deleteUserById(userId);
			response.setData(deleteUserById);
			response.setStatus(true);
			response.setMessage("succsess");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(true);
			response.setMessage("Fail");
		}

	}

	@GetMapping("/generate/excel")
	public Response generateSampleExcellSheet() {
		Response response = new Response();

		try {

			String msg = serviceImpl.generateExcelFile();

			response.setData(msg);
			response.setMessage("Success");
			response.setStatus(true);
			response.setTotalRecords(null);

		} catch (IOException e) {
			e.printStackTrace();

			response.setData(new ArrayList<>());
			response.setMessage("Failed");
			response.setStatus(false);
			response.setTotalRecords(0);
		}

		return response;
	}

	@PostMapping(value = "/uploadexcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response uploadExcelFile(@RequestParam("file") MultipartFile file) {

		Response response = new Response();

		try {
			String saveDataFromExcelFile = serviceImpl.saveDataFromExcelFile(file);
			response.setStatus(true);
			response.setMessage("Excel data saved successfully");
			response.setData(saveDataFromExcelFile);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage(e.getMessage());
			response.setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Failed to upload Excel: ");
			response.setData(null);
		}
		return response;
	}

//====================================================================================================================================

	@PostMapping(value = "/uploadexcelfile1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

	public Response excelBatchProcessingbatchprocessing(@RequestParam("file") MultipartFile file) {

		Response response = new Response();

		try {
			String excelBatchProcessingbatchprocessing = serviceImpl.excelBatchProcessingbatchprocessing(file);
			
			response.setStatus(true);
			response.setMessage("Excel data saved successfully");
			response.setData(excelBatchProcessingbatchprocessing);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Failed to upload Excel: " + e.getMessage());
			response.setData(null);
		}
		return response;
	}
}