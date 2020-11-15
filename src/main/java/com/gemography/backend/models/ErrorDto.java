package com.gemography.backend.models;

import lombok.Data;

@Data
public class ErrorDto {
	private String message;
	private String resource;
	private String field;
	private String code;
}
