package com.gemography.backend.models;

import lombok.Data;

/**
* Model represent GitHub Repository
*
* @author  Youness EL AACHIQ
* @version 1.0
* @since   2020-11-14
*/
@Data
public class GitHubRepo {
	private String url;
	private String language;
}
