package com.minigame2.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Player {
	
	private ArrayList<Item> backpack;
	
	public Player() {
		backpack = new ArrayList<Item>();
	}
	//Returns the inventory list in backpack
	public ArrayList<Item> getBackpack(){
		return this.backpack;
	}
	//add items to the backpack
	public void addItemToBackpack(Item item) {
		this.backpack.add(item);
		System.out.println(item+" has been added");
	}
	
	//Removes item from backpack
	public void removeFromBackpack(Item item) {
		this.backpack.remove(item);
	}

}
