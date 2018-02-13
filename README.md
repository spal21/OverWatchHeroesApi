######################   OverWatchHeroesApi   ###################### 

Rest API that provides information about Overwatch heroes and their abilities.

Technology Used- Java 8, Spring Boot, Spring MVC, Swagger, Maven, Git

Model : 
	2 models used - Hero ,Ability

Architecture - N-layered
	DAO Layer
	Service Layer
	Service Interface(Controller)

Implementation
	The unofficial Overwatch API heroes and abilities data(json) are dumped into appropriate resources packages.
	
	DAO Layer - During initialisation reads all the data from these json objects and populates the model and persists them in local cache(ConcurrentHashMap).
	
	Services Layer - Facilitates the data to the controller
	
	Service Interface(Controller)- Exposes REST interfaces
	
	Swagger - For documentation of the web service
	
	Framework - SpringBoot
	
	Server - EmbeddedTomcat

Launcher class - com.dojo.overwatch.heroes.component.config.Application
Swagger URL - http://localhost:8083/OverWatchHeroesApi/swagger-ui.html
