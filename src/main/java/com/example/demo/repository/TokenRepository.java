package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

}
