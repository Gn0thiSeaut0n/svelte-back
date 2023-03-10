package com.example.svelte.practice.web.config.advice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@NoArgsConstructor
public class ErrorResponse {

	private String message;
	private Integer status;
	private List<FieldError> errors;

	public ErrorResponse(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public ErrorResponse(Integer status, String message, BindingResult bindingResult) {
		this.status = status;
		this.message = message;
		this.errors = bindingResult.getFieldErrors().stream()
				.map(innerObject -> new FieldError(
						innerObject.getObjectName(),
						innerObject.getField(),
						innerObject.getDefaultMessage() != null ? innerObject.getDefaultMessage() : "필수값을 입력해주세요."
					)
				)
				.collect(Collectors.toList());
	}

}
