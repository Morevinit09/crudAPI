package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByPannumber(String pannumber);

	Optional<UserEntity> findByAadharnumber(String aadharNumber);

	Optional<UserEntity> findByMobileNumber(String mobileNumber);

	Optional<UserEntity> findByUseraddress1(String useraddress1);

	Optional<UserEntity> findByUseraddress2(String useraddress2);

	Optional<UserEntity> findByUseraddress3(String useraddress3);

	

	
}
