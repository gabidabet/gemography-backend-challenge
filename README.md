# Gemography Backend challenge

I created that project for gemography Backend coding challenge.

Video explain the code source of my project : [Code source explained](https://www.youtube.com/watch?v=gSJdmiFy6GA&feature=youtu.be)
## What is the project about 
-   It is a REST microservice that list the languages used by the 100 trending public repos on GitHub.
-   For every language, I calculate the attributes below 👇:
    -   Number of repos using this language
    -   The list of repos using the language

## Project Dependencies
Project created using :
-   Java programming language
-   Spring Framework
    - Spring MVC
    - Spring Reactive Web
- Lombok

## Project Architecture :
Project Tree :
```
+---main
|   +---java
|   |   \---com
|   |       \---gemography
|   |           \---backend
|   |               |   BackendCodingChallengeSpringFrameworkApplication.java
|   |               |
|   |               +---constants
|   |               |       GitHubApiEndPoints.java 
|   |               |
|   |               +---exceptions
|   |               |       GitHubApiException.java
|   |               |
|   |               +---models
|   |               |       ErrorDto.java
|   |               |       GitHubErrorResponse.java
|   |               |       GitHubRepo.java
|   |               |       GitHubRepositoriesResponse.java
|   |               |       GitHubResponse.java
|   |               |       Language.java  
|   |               |
|   |               +---rest
|   |               |       GitHubRepositoriesController.java
|   |               |       RestConfiguration.java
|   |               |
|   |               \---util
|   |                       Utils.java
|   |
|   \---resources
|       |   application.properties
|       |
|       +---static
|       \---templates
\---test
    \---java
        \---com
            \---gemography
                \---backend
                        BackendCodingChallengeSpringFrameworkApplicationTests.java
```
**I explained every class in the video if the comments in code source aren't enough**

## Running the project
-   Clone the repo
-   Install dependencies in pom.xml
-   Run as Spring boot application
-   Call the endpoint ```http://localhost:8080/languages/mostused```
-   More information how to call this endpoint visit github api documentation page [here](https://docs.github.com/en/free-pro-team@latest/rest)

## I hope you take this in consideration
-   It's my first time working with Spring web flux
-   It's my first time working with github api
-   Created In two days


