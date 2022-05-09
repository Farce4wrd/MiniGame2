package com.minigame2.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ROOM")
public class GameRoom implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private String hasvisited;
	@OneToMany(mappedBy="room",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Item> items;
	@OneToMany(mappedBy="room", fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Exit> exits = new ArrayList<>();
	@OneToMany(mappedBy="room", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Monster> monsters;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="character_id")
	private Character character;
	
	
	
	/**non-parameter Constructor
	 *  
	 */
	public GameRoom() {
		
	}
	
	//Constructor
	public GameRoom(int Id) {
		this.id = Id;
	}
	
	/**Major GameRoom Constructor
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param hasVisited
	 * @param items
	 * @param exits
	 */
	public GameRoom(String name, String description, String hasVisited, List<Item> items) {
		super();
		this.name = name;
		this.description = description;
		this.hasvisited = hasVisited;
		this.items = items;
	//	this.exits = exits;
//		this.monsters=monsters;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the hasVisited
	 */
	public String isHasVisited() {
		return hasvisited;
	}
	/**
	 * @param hasvisited the hasVisited to set
	 */
	public void setHasVisited() {
		this.hasvisited = "TRUE";
	}
	
	/**To add items to a room
	 * 
	 * Method: @param item
	 *
	 * void
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	/**To remove items from room
	 * 
	 * Method: @param item
	 *
	 * void
	 */
	public void removeItem(Item item) {
		this.items.remove(item);
	}
	
	/**Returns all items inside a room
	 * 
	 * Method: @return
	 *
	 * ArrayList<Item>
	 */
	public List<Item> getItems(){
		return this.items;
	}
	
	/**To get exits directions like {West,North, East, South} on a room-by-room basis
	 * 
	 * Method: @return
	 *
	 * ArrayList<String>
	 */
	public ArrayList<String> getExits(){
		ArrayList<String> exitDirections = new ArrayList<String>();
		for(Exit ex: exits) {
			String direction = ex.getDirection();
			exitDirections.add(direction);
		}
		return exitDirections;
	}
	
	/**To retrieve all exit objects
	 * 
	 * Method: @return
	 *
	 * ArrayList<Exit>
	 */
	public List<Exit> getAllExitObject(){
		return this.exits;
	}
	
	@Override
	public String toString() {
		return "\n GameRoom \n id=" + getId() + "\n name=" + getName() + "\n description=" + getDescription() + "\n hasvisited=" + hasvisited
				+ "\n items=" + getItems() + "\n character=" + character + "\n monsters=" + getMonsters() + "\n exists=" + getExits() + "\n";
	}

	public List<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
