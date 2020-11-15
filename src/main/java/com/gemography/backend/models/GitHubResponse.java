package com.gemography.backend.models;

import java.util.List;

import lombok.Data;

/**
* Abstract Generic Model represent GitHub Response
*
* @author  Youness EL AACHIQ
* @version 1.0
* @since   2020-11-15
*/


@Data

abstract class GitHubResponse<T> {
	private List<T> items;
}
