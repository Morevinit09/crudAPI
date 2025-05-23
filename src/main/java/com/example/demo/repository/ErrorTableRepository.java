package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ErrorTable;

public interface ErrorTableRepository extends JpaRepository<ErrorTable,Long>{
	
//	public Optional<ErrorTableRepository> saveDataFromExcelFile(Integer nomineeId, Character status);

}
