package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Nominee;

public interface NomineeRepository extends JpaRepository<Nominee, Integer> {
	
	public Optional<Nominee> findByNomineeIdAndStatus(Integer nomineeId, Character status);

}
