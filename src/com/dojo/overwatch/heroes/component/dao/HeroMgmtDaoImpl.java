package com.dojo.overwatch.heroes.component.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.dojo.overwatch.heroes.model.Ability;
import com.dojo.overwatch.heroes.model.Hero;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Repository
public class HeroMgmtDaoImpl implements HeroMgmtDAO{
	private static final Logger LOGGER = Logger.getLogger(HeroMgmtDaoImpl.class.getName());
	private ConcurrentMap<Integer,Hero> heroMap = new ConcurrentHashMap<>();
	private ConcurrentMap<Integer,Ability> abilityMap = new ConcurrentHashMap<>();
	private ConcurrentMap<Integer,List<Integer>> heroAbilityMap = new ConcurrentHashMap<>();
	
	@Value(value="${json.herofile.location}")
	private String jsonHeroFileLocation;
	
	@Value(value="${json.abilityfile.location}")
	private String jsonAbilityFileLocation;
	
	
	@PostConstruct
	public void init(){
		try{
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.setPropertyNamingStrategy(
				    PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			
			loadAllHeroesFromJSONFile(mapper);
			loadAbilities(mapper);
			
			
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, ExceptionUtils.getFullStackTrace(e));
		}
		
	}
	
	private void loadAbilities(ObjectMapper mapper) throws JsonParseException, JsonMappingException, IOException{
		
		for(File config : new File(jsonAbilityFileLocation).listFiles()){
			JsonNode abilityDataNode = mapper.readTree(config);
			Ability[] abilityObj = mapper.readValue(abilityDataNode.get("data").toString(), Ability[].class);
			List<Ability> allAbility = Arrays.asList(abilityObj);
			allAbility.forEach(ability->{
				this.abilityMap.put(ability.getId(), ability);
				if(!this.heroAbilityMap.containsKey(ability.getHero().getId())){
					this.heroAbilityMap.put(ability.getHero().getId(),new ArrayList<Integer>());
				}
				this.heroAbilityMap.get(ability.getHero().getId()).add(ability.getId());
			});
		}
		LOGGER.info("Abilities loaded : "+abilityMap.size());
		LOGGER.info(abilityMap.toString());
	}
	
	private void loadAllHeroesFromJSONFile(ObjectMapper mapper) throws JsonProcessingException, IOException {
		for(File config : new File(jsonHeroFileLocation).listFiles()){
			JsonNode heroDataNode = mapper.readTree(config);
			Hero[] obj = mapper.readValue(heroDataNode.get("data").toString(), Hero[].class);
			List<Hero> allHero = Arrays.asList(obj);
			allHero.forEach(hero->{
				this.heroMap.put(hero.getId(), hero);
			});
		}
		
			LOGGER.info("Heroes loaded : "+heroMap.size());
			LOGGER.info(heroMap.toString());
	}
	
	@Override
	public Collection<Hero> getAllHeroes() {
		return heroMap.values();
	}

	@Override
	public Collection<Ability> getAllAbilities() {
		return abilityMap.values();
	}

	@Override
	public Hero findHeroById(int id) {
		return heroMap.get(id);
	}

	@Override
	public Ability findAbilityById(int id) {
		return abilityMap.get(id);
	}

	@Override
	public List<Ability> findAbilityListByHeroId(int id) {
		List<Ability> abilityList = new ArrayList<>();
		heroAbilityMap.get(id).forEach(abilityId -> {Ability a1 = abilityMap.get(abilityId);
				Ability newAbility = new Ability(a1.getId(),a1.getDescription(),a1.getName(),a1.isUltimate(),null);
				abilityList.add(newAbility);
				});;
		return abilityList;
	}
}
