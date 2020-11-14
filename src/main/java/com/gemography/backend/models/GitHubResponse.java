package com.gemography.backend.models;

import java.util.List;

import lombok.Data;

@Data

abstract class GitHubResponse<T> {
	private List<T> items;
}
