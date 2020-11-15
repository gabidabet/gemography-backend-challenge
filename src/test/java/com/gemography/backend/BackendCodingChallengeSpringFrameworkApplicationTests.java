package com.gemography.backend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.gemography.backend.models.Language;
import com.gemography.backend.rest.GitHubRepositoriesController;
import com.gemography.backend.rest.RestConfiguration;

@SpringBootTest
class BackendCodingChallengeSpringFrameworkApplicationTests {
	RestConfiguration restConfiguration = new RestConfiguration();
	@Test
	void contextLoads() {
	}
	@Test
   public void getMostUsedLanguages() throws Exception {
		GitHubRepositoriesController gitHubRepositoriesController = new GitHubRepositoriesController();
		gitHubRepositoriesController.setWebClient(restConfiguration.webClient());
		List<Language> languages = gitHubRepositoriesController.getMostUsedLanguages(new LinkedMultiValueMap<>());
		assertTrue(languages.size() > 0);
   }
}
