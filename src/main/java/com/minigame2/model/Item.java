package com.minigame2.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITEM", schema="ITEM")
public class Item implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String name;
	private String description;
	private String variety;
	private String upgrade;
	private String monstertype;
	private String level;
	private String damage;
	@ManyToOne
	@JoinColumn(name="items_id")
	private GameRoom room;
	
	public Item() {
		
	}
	
	public Item(String name, String descrip, String variety, String monsterType) {
		this.name = name;
		this.description= descrip;
		this.variety = variety;
		this.monstertype = monsterType;
	}
	
	public Item(String name, String descrip, String variety) {
		this.name= name;
		this.description = descrip;
		this.variety = variety;
	}
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		Id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return description;
	}
	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.description = itemDescription;
	}
	@Override
	public String toString() {
		return "Item ID: "+this.getId()+"\n Item name: "+this.getName()+"\n Item description: "+this.getItemDescription()+" ";
		//return "Item [Id=" + Id + ", name=" + name + ", itemDescription=" + itemDescription + "]";
	}


	public String getVariety() {
		return variety;
	}


	public void setVariety(String variety) {
		this.variety = variety;
	}


	public GameRoom getRoom() {
		return room;
	}


	public void setRoom(GameRoom room) {
		this.room = room;
	}

	public String getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(String upgrade) {
		this.upgrade = upgrade;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMonstertype() {
		return monstertype;
	}

	public void setMonstertype(String monstertype) {
		this.monstertype = monstertype;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

}
