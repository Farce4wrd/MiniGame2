package com.minigame2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Monster {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private boolean isBoss;
	private String name; 
	private int hp;
	private int damage;
	
	private Monster(Integer id, boolean isBoss, String name, int hp, int damage) {
		this.id = id;
		this.isBoss = isBoss;
		this.name = name;
		this.hp = hp;
		this.damage = damage;
	}

	public boolean isBoss() {
		return isBoss;
	}

	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
}
