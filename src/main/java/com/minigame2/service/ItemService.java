package com.minigame2.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minigame2.model.Item;
import com.minigame2.model.Player;

@Service
public class ItemService {
	
	@Autowired
	private Player player;
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public void inspect(Item item) {
		System.out.println(item.getItemDescription());
	}
	
	//create Items from txt file to add to a room
	public void createItems(int id, String name, String description) {
		Item item = new Item(id, name, description);
		items.add(item);
		
	}
	
	public ArrayList<Item> getItem() {
		return this.items;
	}
	
	//Removes item from the backpack and places it inside a room
	public void removeItem(Item item) {
		for(Item it: player.getBackpack()) {
			if(it.getName().equals(item.getName())) {
				player.removeFromBackpack(it);
				
			}
		}
	}
	
	
	
	
	

}
