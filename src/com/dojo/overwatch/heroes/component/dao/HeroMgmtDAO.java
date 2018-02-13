package com.dojo.overwatch.heroes.component.dao;

import java.util.Collection;
import java.util.List;

import com.dojo.overwatch.heroes.model.Ability;
import com.dojo.overwatch.heroes.model.Hero;

/**
 * @author sourav
 *
 */
public interface HeroMgmtDAO {
	
	/**
	 * Fetch All Hero Info
	 * @return List of Heros
	 */
	public Collection<Hero> getAllHeroes();
	
	/** 
	 * Fetch All Ability Info
	 * @return List of Abilities
	 */
	public Collection<Ability> getAllAbilities();
	
	/**
	 * Fetch Hero by ID
	 * @param id
	 * @return Hero object
	 */
	public Hero findHeroById(int id);
	

	/**
	 * Fetch Ability by ID
	 * @param id
	 * @return AbilityObject
	 */
	public Ability findAbilityById(int id);
	
	/**
	 * Fetch All abilities for Hero
	 * @param Hero Id
	 * @return List of ABilities
	 */
	public List<Ability> findAbilityListByHeroId(int id);
}
