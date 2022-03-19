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
	public GameRoomController(GameRoomService service, ItemService itemService, Player player) throws GameDataException {
		this.gameRoomService = service;
		this.itemService = itemService;
		this.player = player;
		ControllerStart();
	}
	
	
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
	
	//Provides the exit directions in a string arraylist for view component to hold user options
	public ArrayList<String> getRoomDirections(int id) throws GameDataException{
		return gameRoomService.getRoomDirection(id);
	}
	
	//Gets the next room id in the exit list
	public int getRoomID(GameRoom room, String direction) throws GameDataException {
		return gameRoomService.getNextRoomId(room, direction);
	}
	//Provides the game rooms for the view component to iterate over if needed
	public ArrayList<GameRoom> getAllRooms() throws GameDataException{
		return gameRoomService.getRooms();
	}
	//Tells the user if the game has been visited
	public void setRoomVisit(GameRoom room) throws GameDataException{
		 gameRoomService.setRoomVisited(room);
	}
	
	//Calls the drop action in service --Drops item in room
	public void dropItem(Item item, GameRoom room) {
		gameRoomService.dropItemInRoom(item, room);
		System.out.println(item.getName() + " has been removed");
	}
	
	//To get items within a room for game view class
	public void getItem(GameRoom room) {
		ArrayList<Item> items = gameRoomService.getItemFromRoom(room);
		if(items!=null) {
			for(Item item: items) {
				System.out.println("Oh! There's something here..");
				System.out.println(item.getItemDescription());
			}
		}else {
			System.out.println("No items here");
		}
		
	}
	
	//Checks each user input for changes to item or other things
	public boolean verify(GameRoom room, String playerChoice) throws GameDataException {
		boolean result = false;
		//for user to respond to item/
		if(playerChoice.contains("remove")) {
			boolean itemFound = false;
			for(Item it: player.getBackpack()) {
				if(playerChoice.equals("remove" +it.getName())) {
					itemFound = true;
					dropItem(it, room);
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
				if(playerChoice.equals("get " +it.getName())) {
					itemInRoom = true;
					player.addItemToBackpack(it);
				}
			}
			if(itemInRoom == false) {
				throw new GameDataException("Item can not be found in this room.");
			}
			result = true;
		}
		
		//checks for inventory in backpack
		if(playerChoice.equalsIgnoreCase("backpack")) {
			ArrayList<Item> inventory = player.getBackpack();
			if(inventory.isEmpty()==true) {
				System.out.println("There's nothing in your backpack");
			}else {
				for(Item item: inventory) {
					System.out.println(item + " is inside your backpack");
				}
			}
			result = true;
		}
		
		//if user wants to inspect an item
		if(playerChoice.contains("inspect")) {
			boolean itemInRoom = false;
			for(Item it: room.getItems()) {
				if(playerChoice.equals("inspect " +it.getName())) {
					itemInRoom = true;
					itemService.inspect(it);
				}
			}
			if(itemInRoom == false) {
				throw new GameDataException("Sorry no item in this room to inspect");
			}
			result = true;
			
		}
		return result; //signifies we didnt do anything in room
	}
}
