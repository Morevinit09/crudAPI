package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public String addUserInfo(UserEntity userEntity) {
		repository.save(userEntity);
		return "Added";
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public UserEntity update(Long userId, UserEntity uentity) {

		Optional<UserEntity> user = repository.findById(userId);

		if (user.isPresent()) {

			UserEntity userEntity = user.get();

			userEntity.setUserName(uentity.getUserName());
			userEntity.setEmail(uentity.getEmail());
			userEntity.setMobileNumber(uentity.getMobileNumber());

			return repository.save(userEntity);
		} else {

			throw new IllegalArgumentException("User with ID " + userId + " does not exist");
		}
	}

	@Override
	public String deleteUserById(Long userId) {
		repository.deleteById(userId);
		return "Deleted Successfully";
	}

}
