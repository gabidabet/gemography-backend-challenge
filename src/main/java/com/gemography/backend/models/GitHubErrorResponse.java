package com.gemography.backend.models;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
/**
* Model represent GitHub error Response for Repositories
*
* @author  Youness EL AACHIQ
* @version 1.0
* @since   2020-11-15
*/
@Data	
public class GitHubErrorResponse {
	private String message;
	private String documentationUrl;
	private List<ErrorDto> errors;
	private HttpStatus httpStatus;
}
