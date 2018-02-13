package com.dojo.overwatch.heroes.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@XmlRootElement(name = "hero")
public class Hero {

	private int id;
	private String name;
	private String realName;
	private String health;
	private String armour;
	private String shield;
	@JsonIgnore
	private List<Ability> abilities;
	
	
	public Hero(){}
	
	public Hero(int id, String name, String realName, String health, String armour, String shield) {
		this.id = id;
		this.name = name;
		this.realName = realName;
		this.health = health;
		this.armour = armour;
		this.shield = shield;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getArmour() {
		return armour;
	}
	public void setArmour(String armour) {
		this.armour = armour;
	}
	
	public String toString() {
		String jsonStr = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonStr = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			jsonStr = null;
		}
		return jsonStr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((armour == null) ? 0 : armour.hashCode());
		result = prime * result + ((health == null) ? 0 : health.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((shield == null) ? 0 : shield.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hero other = (Hero) obj;
		if (armour == null) {
			if (other.armour != null)
				return false;
		} else if (!armour.equals(other.armour))
			return false;
		if (health == null) {
			if (other.health != null)
				return false;
		} else if (!health.equals(other.health))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (shield == null) {
			if (other.shield != null)
				return false;
		} else if (!shield.equals(other.shield))
			return false;
		return true;
	}
	public String getShield() {
		return shield;
	}
	public void setShield(String shield) {
		this.shield = shield;
	}
	@JsonGetter("abilities")
	public List<Ability> getAbilities() {
		return abilities;
	}
	@JsonIgnore
	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}
}
