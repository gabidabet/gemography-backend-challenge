package com.gemography.backend.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
/**
* Model represent my json response.
*
* @author  Youness EL AACHIQ
* @version 1.0
* @since   2020-11-15
*/
@Data
public class Language {
	private List<GitHubRepo> reposList;
	private String name;
	
	public Language(String name) {
		this.name = name;
		reposList = new ArrayList<GitHubRepo>();
	}
	public int getNumberOfRepos() {
		return reposList.size();
	}
}
