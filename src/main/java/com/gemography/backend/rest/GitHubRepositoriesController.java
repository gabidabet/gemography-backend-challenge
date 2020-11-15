package com.gemography.backend.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.CodeFlow.ClinitAdder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.gemography.backend.constants.GitHubApiEndPoints;
import com.gemography.backend.exceptions.GitHubApiException;
import com.gemography.backend.models.GitHubErrorResponse;
import com.gemography.backend.models.GitHubRepositoriesResponse;
import com.gemography.backend.models.Language;
import com.gemography.backend.util.Utils;

import reactor.core.publisher.Mono;
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
	 @GetMapping("/languages/mostused")
	 public List<Language> getRepositories(HttpServletRequest request, @RequestParam MultiValueMap<String,String> params) {
		 //the day befor 30 days
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.DATE, -30);
		 Date dateBefore = cal.getTime();
		 
		 // defautl queries for this endpoint
		 MultiValueMap<String,String> defaultQueries = new LinkedMultiValueMap<>();
		 List<String> q = new ArrayList<>();
		 q.add(String.format("created:>%s", sdf.format(dateBefore)));
		 List<String> sort = new ArrayList<String>();
		 sort.add("stars");
		 List<String> desc = new ArrayList<String>();
		 desc.add("desc");
		 defaultQueries.put("q",q);
		 defaultQueries.put("sort",sort);
		 defaultQueries.put("order",desc);
		 // HashMap : name of the Language -> the language reference
		 Map<String,Language> helperMap = new HashMap<>();
		 
		 //Merge the params sent by user and default params to make user benefit from all functions given by github api
		 Utils.mergeMaps(defaultQueries, params);
		 System.out.println(params);
		 // the uri for search repositories
		 String uri = UriComponentsBuilder.fromUriString(GitHubApiEndPoints.SEARCH_REPOSITORIES)
	                .queryParams(params)
	                .build().toUriString();
		 
		 // map the json response to My Model 
		 GitHubRepositoriesResponse response = webClient.get().uri(uri)
				 .retrieve()
				 .onStatus(HttpStatus::is4xxClientError, clientResponse  -> {
					 return clientResponse.bodyToMono(GitHubErrorResponse.class).flatMap(error -> {
		                    return Mono.error(new GitHubApiException(error));
		               });
				 })
				 .bodyToMono(GitHubRepositoriesResponse.class) 
				 .block();
		 
		 //the fun part of reordering the data (i probably will use that algorithm just once so I didn't delegate it to a function or another class)
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
	 
	 @ExceptionHandler({ GitHubApiException.class })
	 public ResponseEntity<Object> handleGitHubApiException(GitHubApiException ex, WebRequest request) {
		 GitHubErrorResponse error = ex.getGitHubErrorResponse();
	       
	     return new ResponseEntity<Object>(
	    		 error, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	 }
}
