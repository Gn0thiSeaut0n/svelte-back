package com.example.svelte.practice.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;

    private String name;
}
