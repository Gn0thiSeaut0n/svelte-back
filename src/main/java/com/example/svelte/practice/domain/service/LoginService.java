package com.example.svelte.practice.domain.service;


import com.example.svelte.practice.domain.dao.LoginDAO;
import com.example.svelte.practice.domain.dto.User;
import com.example.svelte.practice.web.common.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginDAO loginDAO;

    public User login(UserInfo info) {

        return loginDAO.findByUserId(info.getId())
                .orElse(null);
    }
}