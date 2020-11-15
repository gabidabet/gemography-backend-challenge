package com.gemography.backend.exceptions;

import com.gemography.backend.models.GitHubErrorResponse;

import lombok.Data;

@Data
public class GitHubApiException extends Exception {
	private GitHubErrorResponse gitHubErrorResponse;
	public GitHubApiException(GitHubErrorResponse gitHubErrorResponse) {
		super(gitHubErrorResponse.getMessage());
		this.gitHubErrorResponse = gitHubErrorResponse;
	}
}
