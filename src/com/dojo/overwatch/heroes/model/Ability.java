package com.dojo.overwatch.heroes.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@XmlRootElement(name = "ability")
public class Ability {
	private int id;
	private String name;
	private String description;
	private boolean ultimate;
	@JsonIgnore
	private Hero hero;
	
	public Ability(){}
	
	public Ability(int id, String name, String description, boolean ultimate, Hero hero) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.ultimate = ultimate;
		this.hero = hero;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isUltimate() {
		return ultimate;
	}
	public void setUltimate(boolean ultimate) {
		this.ultimate = ultimate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (ultimate ? 1231 : 1237);
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
		Ability other = (Ability) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ultimate != other.ultimate)
			return false;
		return true;
	}
	
	 @JsonGetter("hero")
	public Hero getHero() {
		return hero;
	}
	 @JsonSetter("hero")
	public void setHero(Hero hero) {
		this.hero = hero;
	}
}