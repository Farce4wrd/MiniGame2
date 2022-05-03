package com.minigame2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITEMS")
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String name;
	private String itemDescription;
	@ManyToOne
	@JoinColumn(name="items_id")
	private GameRoom room;
	
	public Item(int id, String name, String itemDescrip) {
		this.Id = id;
		this.name= name;
		this.itemDescription = itemDescrip;
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
		return itemDescription;
	}
	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	@Override
	public String toString() {
		return "Item ID: "+this.getId()+"\n Item name: "+this.getName()+"\n Item description: "+this.getItemDescription()+" ";
		//return "Item [Id=" + Id + ", name=" + name + ", itemDescription=" + itemDescription + "]";
	}

}
