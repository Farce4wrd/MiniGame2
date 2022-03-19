package com.minigame2.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minigame2.exception.GameDataException;
import com.minigame2.model.Exit;
import com.minigame2.model.GameRoom;
import com.minigame2.model.Item;

@Service
public class GameRoomService {
	
	//For connecting to database (if need be)
	//private GameRoomRepository gameRoomRepository;
	
	private ArrayList<GameRoom> gameRooms = new ArrayList<GameRoom>();
	
	public void createRoom(int id, String name, String description, boolean hasVisited, ArrayList<Item> items, ArrayList<Exit> exits) {
		GameRoom room = new GameRoom(id, name, description, hasVisited, items, exits);
		this.gameRooms.add(room);
		
	}
	//Retrieve all the rooms
	public void listAllRooms() {
		for(GameRoom room: gameRooms) {
			System.out.println(room);
		}
	}
	
	//code to get the rooms created in an arraylist
	public ArrayList<GameRoom> getRooms() {
		return this.gameRooms;
	}
	
	//Code to get room exit directions based on the room id
	public ArrayList<String> getRoomDirection(int roomId) throws GameDataException{
		ArrayList<String> roomDirections = new ArrayList<String>();
		for(GameRoom room: gameRooms) {
			if(room.getId() == roomId) {
				roomDirections = room.getExits();
			}
		}
		return roomDirections;
	}
	
	public int getNextRoomId(GameRoom room, String direction) throws GameDataException {
		int result = 0;
		for(Exit ex: room.getAllExitObject()) {
			if(ex.getDirection().equals(direction)) {
				result = ex.getRoomId();
			}
		}
		return result;
		
	}
	
	//Inform the user the room has been visited
	public void setRoomVisited(GameRoom room) {
		room.setHasVisited();
	}
	
	//leaves item from backpack in current room
	public void dropItemInRoom(Item item, GameRoom room) {
		room.addItem(item);
	}
	
	//adds an item randomly into a randomly into a room 
	public void addItemInRoom(ArrayList<Item> item, ArrayList<GameRoom> room) throws GameDataException {
		Item item1 = item.get(0);
		Item item2 = item.get(1);
		Item item3 = item.get(2);
		
		room.get(1).addItem(item1);
		room.get(2).addItem(item2);
		room.get(3).addItem(item3);
		
//		
//		for(int i =0; i<item.size(); i++) {
//				item1 = item.get(1);
//				item2 = item.get(2);
//				item3 = item.get(3);
//			}
			
//		}else {
//			throw new GameDataException("Problem occured trying to place items in room");
//		}
		
	}
	
	//remove item from room after player picks it up
	public void removeItemFromRoom(Item item, GameRoom room) {
		room.removeItem(item);
	}
	
	//gets item from specific room
	public ArrayList<Item> getItemFromRoom(GameRoom room) {
		return room.getItems();
	}
	
	

	
	

}
