package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Nominee;

public interface NomineeRepository extends JpaRepository<Nominee, Integer> {

}
