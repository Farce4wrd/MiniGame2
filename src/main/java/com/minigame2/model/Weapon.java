package com.minigame2.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


public class Weapon extends Item{
	@GeneratedValue(strategy= GenerationType.AUTO)
	private String name;
	private String description;
	private String variety;
	private static int level;
	private String damage;
	private static String weaponType;
	

	public Weapon(String name, String description, String type, int level, String damage, String weaponType) {
		//this.id = id;
		super(name, description, type);
		//this.name = name;
		//this.description = description;
		this.variety = type;
		this.level = level;
		this.damage = damage;
		this.weaponType = weaponType;
	}

//	public static int getLevel() {
//		return level;
//	}

//	public static void setLevel(int level) {
//		level = level;
//	}



	public static String getWeaponType() {
		return weaponType;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public static void setWeaponType(String weaponType) {
		Weapon.weaponType = weaponType;
	}

//	public Integer getId() {
//		return id;
//	}

//	public void setId(Integer id) {
//		this.id = id;
//	}

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

	public String getType() {
		return variety;
	}

	public void setType(String type) {
		this.variety = type;
	}
	
	

}
