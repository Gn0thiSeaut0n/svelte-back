package com.example.svelte.practice.web.common;

import com.example.svelte.practice.domain.dto.User;
import com.example.svelte.practice.domain.service.LoginService;
import com.example.svelte.practice.web.config.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// 스벨트용 backend

@Slf4j
@RequestMapping("/api")
@RestController

@RequiredArgsConstructor
public class CommonController {
    private final LoginService loginService;
    @PostMapping("/test")
    public ResponseEntity<User> test(@Valid @RequestBody UserInfo info) throws Exception {
        log.info("test in");

        User user = loginService.login(info);

        if(user == null) {
            throw new MemberNotFoundException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}