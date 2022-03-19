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
	public void addItemInRoom(Item item) {
		int randRoom1 = (int) Math.random()*(7-1+1)+1;
		int randRoom2 = (int) Math.random()*(7-1+1)+1;
		int randRoom3 = (int) Math.random()*(7-1+1)+1;
		for(GameRoom room: this.gameRooms) {
			if(room.getId() == randRoom1) {
				room.addItem(item);
			}
			if(room.getId() == randRoom2) {
				room.addItem(item);
			}
			if(room.getId() == randRoom3) {
				room.addItem(item);
			}
		}
		
	}
	
	//remove item from room after player picks it up
	public void removeItemFromRoom(Item item, GameRoom room) {
		room.removeItem(item);
	}
	
	//Find a way to return the list of rooms created to the controller
//	public List<GameRoom> getAllGameRoom(){
//		return gameRoomRepository.findAll();
//	}
	
	//make room
	
	

	
	

}
