package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.QueuTable;

public interface QueuRepository extends JpaRepository<QueuTable,Integer> {
	
	List<QueuTable> findByIsProcess(String isProcess);

}