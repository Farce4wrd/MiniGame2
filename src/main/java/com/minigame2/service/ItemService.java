package com.minigame2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minigame2.data.ItemRepository;
import com.minigame2.model.Item;
import com.minigame2.model.Player;

@Service
public class ItemService {
	private ItemRepository itemRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	private Player player;
	
	/**This code shows the name of the item
	 * 
	 * Method: @param item
	 *
	 * void
	 */
	public void inspect(Item item) {
		System.out.println(item.getItemDescription());
	}
	
	/**create Items from txt file to add to a room
	 * 
	 * Method: @param id
	 * Method: @param name
	 * Method: @param description
	 *
	 * void
	 */
//	public void createItems(int id, String name, String description) {
//		Item item = new Item(id, name, description);
//		items.add(item);
//		
//	}
	
	
	public List<Item> getWeapons(){
		return this.itemRepository.findByVariety("Weapon");
	}
	
	
	
	/**returns items in a list
	 * 
	 * Method: @return
	 *
	 * ArrayList<Item>
	 */
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		for(Item item: this.itemRepository.findAll()) {
			items.add(item);
		}
		return items;
	}
	
	/**Removes item from the backpack and places it inside a room
	 * 
	 * Method: @param item
	 *
	 * void
	 */
	public void removeItem(Item item) {
		for(Item it: player.getBackpack()) {
			if(it.getName().equals(item.getName())) {
				player.removeFromBackpack(it);
				
			}
		}
	}
	
	
	
	
	

}
