package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	//Find a way to return the list of rooms created to the controller
//	public List<GameRoom> getAllGameRoom(){
//		return gameRoomRepository.findAll();
//	}
	
	//make room
	
	

	
	

}
