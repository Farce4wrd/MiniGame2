package com.minigame2.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.minigame2.exception.GameDataException;
import com.minigame2.model.Exit;
import com.minigame2.model.GameRoom;
import com.minigame2.model.Item;
import com.minigame2.model.Player;
import com.minigame2.service.GameRoomService;
import com.minigame2.service.ItemService;

@Controller
public class GameRoomController {
	
	private GameRoomService gameRoomService;
	private ItemService itemService;
	private Player player;
	
	
	@Autowired
	/**Main controller responsible for all calls
	 * 
	 * @param service
	 * @param itemService
	 * @param player
	 * @throws GameDataException
	 */
	public GameRoomController(GameRoomService service, ItemService itemService, Player player) throws GameDataException {
		this.gameRoomService = service;
		this.itemService = itemService;
		this.player = player;
		ControllerStart();
	}
	
	/**Creates room and its items
	 * 
	 * Method: @throws GameDataException
	 *
	 * void
	 */
	public void ControllerStart() throws GameDataException {
		try {
			File itemFile = new File("item.txt");
			Scanner itemReader = new Scanner(itemFile);
			while(itemReader.hasNextLine()) {
				int id = Integer.parseInt(itemReader.nextLine());
				String name = itemReader.nextLine();
				String description = itemReader.nextLine();
				itemService.createItems(id, name, description);
			}
		}catch(Exception ex) {
			throw new GameDataException("Error occured while reading the text file");
		}
		
		
		
		try {
			File gameFiles = new File("minigame.txt");
			Scanner fileReader = new Scanner(gameFiles);
			ArrayList<String> navigation = new ArrayList<String>();

			while(fileReader.hasNextLine()) {
				ArrayList<Exit> exitList = new ArrayList<Exit>();
				//String tempLine = fileReader.nextLine();
				String temp = fileReader.next();
				int id = Integer.parseInt(temp);
				fileReader.nextLine(); //to make it skip line
				//int id = Integer.parseInt(fileReader.next());
				String name = fileReader.nextLine();
				//This is to get the room ids separate from the room directions
				Scanner directionsWithId = new Scanner(fileReader.nextLine());
				while(directionsWithId.hasNext()) {
					String direction = directionsWithId.next();
					int exitId = directionsWithId.nextInt();
					Exit exit = new Exit(direction, exitId);
					exitList.add(exit);
				}
				String description = fileReader.nextLine();
				ArrayList<Item> it = new ArrayList<Item>();
				gameRoomService.createRoom(id, name, description, false, it, exitList);
				
			}
			//randomly generate the items in rooms
			gameRoomService.addItemInRoom(itemService.getItem(), gameRoomService.getRooms());

	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cant find game files.");
		}
		
	}
	
	/**Provides the exit directions in a string arraylist for view component to hold user options
	 * 
	 * Method: @param id
	 * Method: @return
	 * Method: @throws GameDataException
	 *
	 * ArrayList<String>
	 */
	public ArrayList<String> getRoomDirections(int id) throws GameDataException{
		return gameRoomService.getRoomDirection(id);
	}
	
	/**Gets the next room id in the exit list
	 * 
	 * Method: @param room
	 * Method: @param direction
	 * Method: @return
	 * Method: @throws GameDataException
	 *
	 * int
	 */
	public int getRoomID(GameRoom room, String direction) throws GameDataException {
		return gameRoomService.getNextRoomId(room, direction);
	}
	/**Provides the game rooms for the view component to iterate over if needed
	 * 
	 * Method: @return
	 * Method: @throws GameDataException
	 *
	 * ArrayList<GameRoom>
	 */
	public ArrayList<GameRoom> getAllRooms() throws GameDataException{
		return gameRoomService.getRooms();
	}
	/**Tells the user if the game has been visited
	 * 
	 * Method: @param room
	 * Method: @throws GameDataException
	 *
	 * void
	 */
	public void setRoomVisit(GameRoom room) throws GameDataException{
		 gameRoomService.setRoomVisited(room);
	}
	
	/**Calls the drop action in service --Drops item in room
	 * 
	 * Method: @param item
	 * Method: @param room
	 *
	 * void
	 */
	public void dropItem(Item item, GameRoom room) {
		gameRoomService.dropItemInRoom(item, room);
		player.removeFromBackpack(item);
		System.out.println(item.getName() + " has been removed");
	}
	
	/**To get items within a room for game view class
	 * 
	 * Method: @param room
	 *
	 * void
	 */
	public void getItem(GameRoom room) {
		ArrayList<Item> items = gameRoomService.getItemFromRoom(room);
		if(items!=null) {
			for(Item item: items) {
				System.out.println("Oh! There's something here..");
				System.out.println(item.getName());
			}
		}else {
			System.out.println("No items here");
		}
		
	}
	
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
					dropItem(it, room);
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
					gameRoomService.removeItemFromRoom(it, room);	//to remove item from room
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
			System.out.println("Exits: "+ gameRoomService.getRoomDirection(room.getId()));
			System.out.println("Items: "+ gameRoomService.getItemFromRoom(room));
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
					itemService.inspect(it);
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
