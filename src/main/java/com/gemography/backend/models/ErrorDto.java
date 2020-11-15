package com.gemography.backend.models;

import lombok.Data;
/**
* Model represent error
*
* @author  Youness EL AACHIQ
* @version 1.0
* @since   2020-11-15
*/
@Data
public class ErrorDto {
	private String message;
	private String resource;
	private String field;
	private String code;
}
