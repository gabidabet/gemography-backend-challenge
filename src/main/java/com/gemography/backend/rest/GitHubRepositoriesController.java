package com.gemography.backend.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.gemography.backend.constants.GitHubApiEndPoints;
import com.gemography.backend.models.GitHubRepositoriesResponse;
import com.gemography.backend.models.Language;
/**
* RestController to handle requests.
*
* @author  Youness EL AACHIQ
* @version 1.0
* @since   2020-11-14
*/
@RestController
public class GitHubRepositoriesController {

	 
	@Autowired
	private WebClient webClient;
		
	// The endpoint name that I chose to give a response ( I didn't find an elegant name )
	 @GetMapping("/search/languages")
	 public List<Language> getRepositories(HttpServletRequest request, @RequestParam MultiValueMap<String,String> params) {
		 // HashMap : name of the Language -> the language reference
		 Map<String,Language> helperMap = new HashMap<>();
		 // the uri for search repositories
		 String uri = UriComponentsBuilder.fromUriString(GitHubApiEndPoints.SEARCH_REPOSITORIES)
	                .queryParams(params)
	                .build().toUriString();
		 // map the json response to My Model 
		 GitHubRepositoriesResponse response = webClient.get().uri(uri)
				 .retrieve()
				 .bodyToMono(GitHubRepositoriesResponse.class)
				 .block();
		 //the fun part of reordering the data
		 response.getItems()
				 .stream()
				 .filter(e-> e.getLanguage() != null) // removing repos that have language attribute equals null
				 .forEach(
						 // add to each language the repositories that using it
						 e -> {
							 if(helperMap.containsKey(e.getLanguage())) {
								 helperMap.get(e.getLanguage()).getReposList().add(e);
							 }else {
								 Language language = new Language(e.getLanguage());
								 language.getReposList().add(e);
								 helperMap.put(e.getLanguage(), language);
							 }
						 }
				  );
		 // return the responce
		 return new ArrayList<Language>(helperMap.values());
	  }
}
