package com.minigame2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MONSTER", schema="MONSTER")
public class Monster {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private boolean isBoss;
	private String name; 
	private int hp;
	private int damage;
	private String variety;
	@ManyToOne
	@JoinColumn(name="monsters_roomid")
	private GameRoom room;
	
	public Monster() {
		
	}
	
	
	private Monster( boolean isBoss, String name, int hp, int damage) {
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

	public String getVariety() {
		return variety;
	}


	public void setVariety(String variety) {
		this.variety = variety;
	}


	@Override
	public String toString() {
		return "Monster [id=" + id + ", isBoss=" + isBoss + ", name=" + name + ", hp=" + hp + ", damage=" + damage
				+ ", variety=" + variety + ", room=" + room + "]";
	}
	
	
}
