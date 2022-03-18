package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GameDataException;
import model.Exit;
import model.GameRoom;
import model.Item;

@Service
public class GameRoomService {
	
	@Autowired
	//For connecting to database (if need be)
	//private GameRoomRepository gameRoomRepository;
	
	private ArrayList<GameRoom> gameRooms = new ArrayList<GameRoom>();
	
	public void createRoom(int id, String name, String description, boolean hasVisited, ArrayList<Item> items, ArrayList<Exit> exits) {
		GameRoom room = new GameRoom(id, name, description, hasVisited, items, exits);
		gameRooms.add(room);
		
	}
	//Retrieve all the rooms
	public void listAllRooms() {
		for(GameRoom room: gameRooms) {
			System.out.println(room);
		}
	}
	
	//code to get the rooms created in an arraylist
	public ArrayList<GameRoom> getRooms() {
		return gameRooms;
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
	
	public void setRoomVisited(GameRoom room) {
		room.setHasVisited();
	}
	//Find a way to return the list of rooms created to the controller
//	public List<GameRoom> getAllGameRoom(){
//		return gameRoomRepository.findAll();
//	}
	
	//make room
	
	

	
	

}
