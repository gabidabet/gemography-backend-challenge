package com.gemography.backend.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

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
