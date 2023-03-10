package com.example.svelte.practice.domain.dao;

import com.example.svelte.practice.domain.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface LoginDAO {
    Optional<User> findByUserId(String id);
}