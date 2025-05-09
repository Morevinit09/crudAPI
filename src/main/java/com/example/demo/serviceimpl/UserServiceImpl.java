package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public String addUserInfo(UserDto userdto) {

		UserEntity userEntity = new UserEntity();
		
		userEntity.setUserName(userdto.getUserName());
		userEntity.setEmail(userdto.getEmail());
		userEntity.setMobileNumber(userdto.getMobileNumber());
		userEntity.setUserAddress(userdto.getUserAddress());
		userEntity.setUserDob(userdto.getDob());
		repository.save(userEntity);
		return "Added";
	}

	@Override
	public List<UserEntity> getAllUsers() {
		List<UserEntity> getall = repository.findAll();
		return getall;
	}

	@Override
	public UserEntity update(Long userId, UserDto userDto) {

		Optional<UserEntity> user = repository.findById(userId);

		if (user.isPresent()) {

			UserEntity userEntity = user.get();

			userEntity.setUserName(userDto.getUserName());
			userEntity.setEmail(userDto.getEmail());
			userEntity.setMobileNumber(userDto.getMobileNumber());

			return repository.save(userEntity);
		} else {

			throw new IllegalArgumentException("User with ID " + userId + " does not exist");
		}
	}

	@Override
	public String deleteUserById(Long userId) {
		Optional<UserEntity> findbyuserid = repository.findById(userId);
		if (findbyuserid.isPresent()) {
			UserEntity userEntity = findbyuserid.get();

			userEntity.setUserStatus("N");
			repository.save(userEntity);
		}

		return "Deleted Successfully";
	}

}
