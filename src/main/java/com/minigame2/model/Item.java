package com.minigame2.model;

public class Item {
	
	private int Id;
	private String name;
	private String itemDescription;
	//private GameRoom room;
	
	public Item(int id, String name, String itemDescrip) {
		this.Id = id;
		this.name= name;
		this.itemDescription = itemDescrip;
	}
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
		return "Item [Id=" + Id + ", name=" + name + ", itemDescription=" + itemDescription + "]";
	}

}
