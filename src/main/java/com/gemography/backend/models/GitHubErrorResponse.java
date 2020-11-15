package com.gemography.backend.models;

import java.util.List;

import lombok.Data;

@Data	
public class GitHubErrorResponse {
	private String message;
	private String documentationUrl;
	private List<ErrorDto> errors;
}
