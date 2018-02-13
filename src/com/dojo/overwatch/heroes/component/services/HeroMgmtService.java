package com.dojo.overwatch.heroes.component.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojo.overwatch.heroes.component.dao.HeroMgmtDAO;
import com.dojo.overwatch.heroes.model.Ability;
import com.dojo.overwatch.heroes.model.Hero;

@Service(value="heroMgmtService")
public class HeroMgmtService {

	@Autowired
	private HeroMgmtDAO dao;
	
	public Collection<Hero> getAllHeroes(){
		return dao.getAllHeroes();
	}
	
	public Collection<Ability> getAllAbilities(){
		return dao.getAllAbilities();
	}
	
	public Hero findHeroById(int id){
		Hero hero = dao.findHeroById(id);
		hero.setAbilities(dao.findAbilityListByHeroId(id));
		return hero;
	}
	
	public List<Ability> findAbilityByHeroId(int id){
		return dao.findAbilityListByHeroId(id);
	}
	
	public Ability findAbilityById(int id){
		return dao.findAbilityById(id);
	}
}
