package com.dojo.overwatch.heroes.web;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.overwatch.heroes.component.services.HeroMgmtService;
import com.dojo.overwatch.heroes.model.Ability;
import com.dojo.overwatch.heroes.model.Hero;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class OverWatchHeroesController {
	private static final Logger LOGGER = Logger.getLogger(OverWatchHeroesController.class.getName());

	
	@Autowired
	private HeroMgmtService heroService;
	
	@ApiOperation(value = "get Hero Ability By ID", produces = "application/json")
	@RequestMapping(value = { "/api/heros/{hero_id}/abilities" }, method = RequestMethod.GET, headers = {
			"Accept=application/json" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public List<Ability> findAbilityByHeroId(@PathVariable("hero_id") int heroID) {
		return heroService.findAbilityByHeroId(heroID);
	}

	@ApiOperation(value = "get Hero Data By ID", produces = "application/json")
	@RequestMapping(value = { "/api/heroes/{hero_id}/" }, method = RequestMethod.GET, headers = {
			"Accept=application/json" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public Hero findHeroById(@PathVariable("hero_id") String heroID) {
		return heroService.findHeroById(Integer.parseInt(heroID));
	}

	@ApiOperation(value = "get Hero List", produces = "application/json")
	@RequestMapping(value = { "/api/heroes" }, method = RequestMethod.GET, headers = { "Accept=application/json" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public Collection<Hero> getAllHeroes() {
		return heroService.getAllHeroes();
	}
	
	@ApiOperation(value = "get Abilities List", produces = "application/json")
	@RequestMapping(value = { "/api/abilities" }, method = RequestMethod.GET, headers = { "Accept=application/json" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public Collection<Ability> getAllAbilities() {
		return heroService.getAllAbilities();
	}
	
	@ApiOperation(value = "get Ability By ID", produces = "application/json")
	@RequestMapping(value = { "/api/abilities/{ability_id}" }, method = RequestMethod.GET, headers = {
			"Accept=application/json" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public Ability findAbilityById(@PathVariable("ability_id") int abilityID) {
		return heroService.findAbilityById(abilityID);
	}

}