package com.minigame2.controller;

import com.minigame2.exception.GameDataException;
import com.minigame2.model.Character;
import com.minigame2.model.*;
import com.minigame2.service.CharacterService;
import com.minigame2.service.GameRoomService;
import com.minigame2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class GameRoomController {
	
	@Autowired
	private GameRoomService gameRoomService;
	@Autowired
	private CharacterService characterService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private Player player;
	
	
	@Autowired
	/**Main controller responsible for all calls
	 * 
	 * @param service
	 * @param itemService
	 * @param player
	 * @throws GameDataException
	 */
	public GameRoomController(GameRoomService gameRoomService, CharacterService characterService)// throws GameDataException 
	{
		this.characterService = characterService;
		this.gameRoomService = gameRoomService;
		this.itemService = itemService;
		this.player = player;
		//ControllerStart();
	}
	

	//////Pre-cursor for the UI
	public Character createCharacterAtBeginning(String name) {
		GameRoom location = this.gameRoomService.getRoom(1);
		Character chara = new Character(name,location,50,3,1,1);
		this.characterService.characterSave(chara);
		return chara;
		
	}
	
	/**Starts player combat loop
	 * 
	 * Method: @param Weapon weapon
	 * Method: @param Monster monster
	 * Method: @param Character character
	 * 
	 */
	public String combat(Weapon weapon, Monster monster, Character character)
	{
		int monsterHp = monster.getHp();
		Random rand = new Random();
		int monsterDamage = rand.nextInt(monster.getDamage());
		String monsterType = monster.getVariety();
		
		int playerHp = character.getHp();
		int weaponDamage = Integer.parseInt(weapon.getDamage());
		String weaponType = weapon.getVariety();
		
		while(monsterHp > 0)
		{
			if(weaponType.equalsIgnoreCase(monsterType))
			{
				character.setHp(playerHp - monsterDamage);
				monster.setHp(monsterHp - weaponDamage);
				return "Your hp: " + playerHp + "\nMonster hp: " + monsterHp;
			}
			else
			{
				character.setHp(playerHp - monsterDamage);
				return "Your hp: " + playerHp + "\nMonster hp: " + monsterHp + "\nWrong weapon type! You did not do any damage";
			}
		}
		if(monsterHp <= 0)
		{
			
			return monster.getName() + " has been defeated!";
		}
		else
		{
			return "\n";
		}
	}
	
	/**Player movement method
	 * 
	 * Method: @param GameRoom room
	 * Method: @param Character character
	 * Note: I un commented the exits arraylist getters and setters to make this work
	 */
	
	public String move(Character character, String direction)
	{
		Boolean isPresent = false;
		GameRoom currentLocation = character.getLocation();
		List<Exit> exits = currentLocation.getAllExitObject();
		for(Exit e : exits)
		{
			if((e.getDirection()).equalsIgnoreCase(direction))
			{
				isPresent = true;
				int i = e.getRoomId();
				character.setLocation(this.gameRoomService.getRoom(i));
				return this.gameRoomService.getRoom(i).getDescription();
			}
		}
		if(isPresent == false) {
			return direction+" does not exist here";
		}
		
		return character.getLocation().getDescription();
		//Change the UI of map to match the player location
	}
	
	public String tester()
	{
		List<String> exits = this.gameRoomService.getRoom(2).getExits();
		String result = ""+exits.size();
		
//		for(String e: exits)
//		{
//			result+=e;
//		}
		return result;
	}
	
	/**Upgrade weapon 
	 * 
	 * Method: @param Character character
	 * Method: @param Weapon weapon
	 * Method: @param Item upgradeConsumable
	 * 
	 */
//	public String upgrade(Character character, Weapon weapon, Item upgradeConsumable)
//	{
//		ArrayList<Item> currentInventory = character.getInventory();
//		int weaponDamage = Integer.parseInt(weapon.getDamage()) ;
//		
//		if((upgradeConsumable.getName().equalsIgnoreCase("Big Box")) && (character.getInventory().contains(upgradeConsumable)
//				&& currentInventory.contains(weapon)))
//		{
//			weaponDamage+=15;
//			weapon.setDamage(""+weaponDamage);
//			return weapon.getName() + " has been upgraded. \n The current damage is now " + weapon.getDamage() + "\n";
//		}
//		else
//		{
//			return "Invalid option. \n You need to have the " + weapon.getName() + " and the Big Box in your inventory to upgrade the " + weapon.getName() + "\n";
//		}
//	}
//	
	/**Pickup an item from a room
	 * 
	 * Method: @param Character character
	 * Method: Item item
	 */
	
	public String pickup(Character character, String item)
	{
		List<Item> characterInventory = character.getInventory();
		
		GameRoom location = character.getLocation();         //got through serializable
		GameRoom location01 = this.gameRoomService.getRoom(location.getId());
		//System.out.println(location01.getItems());
		//This holds all items attached to a room
		//GameRoom presentRoom = this.gameRoomService.getRoom(location.getId());
		
		List<Item> roomInventory = this.itemService.getItemsById(location);  
		System.out.println("The test");
		roomInventory.forEach(itema ->{
			
			System.out.println(itema);
		});
		//Item roomInventory_01= this.itemService.getItemsById(location.getId());
		Boolean isFound = false;
		for(Item itemToFind : roomInventory) {
			if(itemToFind.getName().equalsIgnoreCase(item)) {
				isFound = true;
				characterInventory.add(itemToFind);  //add item to character's inventory
				character.setInventory(characterInventory);
				
				
				//roomInventory changed
				location.removeItem(itemToFind); //removes room item from specific room
				
				//characterService.characterSave(character); //updates the character in the db with new item
				this.gameRoomService.addRoom(location); 
				 //saves state of the room to db
				String res =itemToFind.getName() + " has been added to your inventory!\n";
				
				return res;
			}
		}
		if(isFound == false) {
			return "Invalid option. " + item + " is not in the " + location.getName();
		}
		return "\n";

	}
	
	/**Drop item in a room
	 * 
	 * Method: @param Character character
	 * Method: @param Item item
	 * 
	 */
	
//	public String drop(Character character, Item item)
//	{
//		ArrayList<Item> characterInventory = character.getInventory();
//		GameRoom location = character.getLocation();
//		
//		if(characterInventory.contains(item))
//		{
//			location.getItems().add(item);
//			characterInventory.remove(item);
//			characterService.characterSave(character);
//			return item.getName() + " has been added to the " + location.getName() + "!\n";
//		}
//		else
//		{
//			return "Invalid option. " + item.getName() + " is not in your inventory. \n";
//		}
//	
//	}
//	
	
	/**To get items within a room for game view class
	 * 
	 * Method: @param room
	 *
	 * void
	 */
//	public void getItem(GameRoom room) {
//		ArrayList<Item> items = gameRoomService.getItemFromRoom(room);
//		if(items!=null) {
//			for(Item item: items) {
//				System.out.println("Oh! There's something here..");
//				System.out.println(item.getName());
//			}
//		}else {
//			System.out.println("No items here");
//		}
//		
//	}
	
	/**Checks each user input for changes to item or other things
	 * 
	 * Method: @param room
	 * Method: @param playerChoice
	 * Method: @return
	 * Method: @throws GameDataException
	 *
	 * boolean
	 */
	public boolean verify(GameRoom room, String playerChoice) throws GameDataException {
		boolean result = false;
		//for user to respond to item/
		if(playerChoice.contains("remove")) {
			boolean itemFound = false;
			ArrayList<Item> items = player.getBackpack();
			for(Item it: items) {
				if(playerChoice.equalsIgnoreCase("remove " +it.getName())) {
					itemFound = true;
					//dropItem(it, room);
					break;
				}
			}
			if(itemFound == false) {
				throw new GameDataException("Cant find item in backpack");
			}
			result= true;
		}
		
		
		//looks into stored items within current room
		if(playerChoice.contains("get")) {
			boolean itemInRoom = false;
			for(Item it: room.getItems()) {
				if(playerChoice.equalsIgnoreCase("get " +it.getName())) {
					itemInRoom = true;
					player.addItemToBackpack(it);
					//gameRoomService.removeItemFromRoom(it, room);	//to remove item from room
					break;
				}
			}
			if(itemInRoom == false) {
				System.out.println("Item can not be found in this room.");
				return true;
				//throw new GameDataException("Item can not be found in this room.");
			}
			result = true;
		}
		
		//displays the current room's description, exit and items (if present)
		if(playerChoice.equalsIgnoreCase("look")) {
			System.out.println("Room description: "+ room.getDescription());
//			System.out.println("Exits: "+ gameRoomService.getRoomDirection(room.getId()));
//			System.out.println("Items: "+ gameRoomService.getItemFromRoom(room));
			return true;
			
		}
		
		//checks for inventory in backpack
		if(playerChoice.equalsIgnoreCase("backpack")) {
			ArrayList<Item> inventory = player.getBackpack();
			if(inventory.isEmpty()==true) {
				System.out.println("There's nothing in your backpack");
			}else {
				for(Item item: inventory) {
					System.out.println(item.getName() + " is inside your backpack");
				}
			}
			result = true;
		}
		
		//if user wants to inspect an item
		if(playerChoice.contains("inspect")) {
			boolean itemInRoom = false;
			for(Item it: room.getItems()) {
				if(playerChoice.equalsIgnoreCase("inspect " +it.getName())) {
					itemInRoom = true;
					//itemService.inspect(it);
				}
			}
			if(itemInRoom == false) {
				//throw new GameDataException("Sorry no item in this room to inspect");
				System.out.println("Sorry, no item in this room to inspect");
				return true;
			}
			result = true;
			
		}
		return result; //signifies we didnt do anything in room
	}
}
