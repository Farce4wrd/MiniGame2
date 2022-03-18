package com.minigame2.model;

import java.util.ArrayList;

public class GameRoom {
	private int id;
	private String name;
	private String description;
	private boolean hasVisited;
	private ArrayList<Item> items;
	private ArrayList<Exit> exits;
	//private RoomDB rdb;
	//private ItemDB idb;
	
	
	//non-parameter Constructor 
	public GameRoom() {
		
	}
	//Constructor
	public GameRoom(int Id) {
		this.id = Id;
	}
	
	public GameRoom(int id, String name, String description, boolean hasVisited, ArrayList<Item> items,
			ArrayList<Exit> exits) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.hasVisited = hasVisited;
		this.items = items;
		this.exits = exits;
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
		id = id;
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
	public boolean isHasVisited() {
		return hasVisited;
	}
	/**
	 * @param hasVisited the hasVisited to set
	 */
	public void setHasVisited() {
		this.hasVisited = true;
	}
	
	//To get exits directions like {West,North, East, South} on a room-by-room basis
	public ArrayList<String> getExits(){
		ArrayList<String> exitDirections = new ArrayList<String>();
		for(Exit ex: exits) {
			String direction = ex.getDirection();
			exitDirections.add(direction);
		}
		return exitDirections;
	}
	
	public ArrayList<Exit> getAllExitObject(){
		return this.exits;
	}
	
	@Override
	public String toString() {
		String visit="";
		if(this.isHasVisited()== true) {
			visit ="Has visited";
		}else {
			visit ="Has not visited";
		}
		System.out.println(this.getName()+": "+ visit);
		return this.description;
		//return "GameRoom [description=" + description + "You can go " + result + "]";
	}

}
