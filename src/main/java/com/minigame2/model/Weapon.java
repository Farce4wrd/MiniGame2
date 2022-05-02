package com.minigame2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weapon{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	private String type;
	private static int level;
	private static int damage;
	private static String weaponType;
	

	public Weapon(Integer id, String name, String description, String type, int level, int damage, String weaponType) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.level = level;
		this.damage = damage;
		this.weaponType = weaponType;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		level = level;
	}

	public static int getDamage() {
		return damage;
	}

	
	public static void setDamage(int damage) {
		damage = damage;
	}

	public static String getWeaponType() {
		return weaponType;
	}

	public static void setWeaponType(String weaponType) {
		Weapon.weaponType = weaponType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}