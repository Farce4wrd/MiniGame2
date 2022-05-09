package com.minigame2.service;

import com.minigame2.data.GameRoomRepository;
import com.minigame2.exception.GameDataException;
import com.minigame2.model.GameRoom;
import com.minigame2.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GameRoomService {

	private GameRoomRepository gameRoomRepository;
	
	@Autowired
	public GameRoomService(GameRoomRepository repo) {
		this.gameRoomRepository = repo;
	}
	
	private ArrayList<GameRoom> gameRooms = new ArrayList<GameRoom>();
	
	/**Creates the room object
	 * 
	 * Method: @param id
	 * Method: @param name
	 * Method: @param description
	 * Method: @param hasVisited
	 * Method: @param items
	 * Method: @param exits
	 *
	 * void
	 */
//	public void createRoom(int id, String name, String description, boolean hasVisited, ArrayList<Item> items, ArrayList<Exit> exits) {
//		GameRoom room = new GameRoom(id, name, description, hasVisited, items, exits);
//		this.gameRooms.add(room);
//		
//	}
//	
	//Adds rooom to the database
	public GameRoom addRoom(GameRoom room) {
		return gameRoomRepository.save(room);
	}
	//retrieves rooms from the database
	public List<GameRoom> getRooms(){
		List<GameRoom> rooms = new ArrayList<GameRoom>();
		for(GameRoom room: this.gameRoomRepository.findAll()) {
			rooms.add(room);
		}
		return rooms;
	}
	public void deleteRoom(GameRoom room) {
		this.gameRoomRepository.delete(room);
	}
	
	public GameRoom getRoom(int id)
	{
		return this.gameRoomRepository.findById(id);
	}
	
	public List<GameRoom> getRoomWItems(){
		return this.gameRoomRepository.retrieveAll();
	}
	public GameRoom getRoomWithItem(int id) {
		return this.gameRoomRepository.findGameRoomById(id);
	}
	
//	public void removeItems(Item item) {
//		GameRoom room = this.gameRoomRepository.findByItem(0)
//	}
	/**Retrieve all the rooms
	 * 
	 * Method: 
	 *
	 * void
	 */
//	public void listAllRooms() {
//		for(GameRoom room: gameRooms) {
//			System.out.println(room);
//		}
//	}
	
	/**code to get the rooms created in an arraylist
	 * 
	 * Method: @return
	 *
	 * ArrayList<GameRoom>
	 */
	public ArrayList<GameRoom> getRoomss() {
		return this.gameRooms;
	}
	
	/**Code to get room exit directions based on the room id
	 * 
	 * Method: @param roomId
	 * Method: @return
	 * Method: @throws GameDataException
	 *
	 * ArrayList<String>
	 */
//	public ArrayList<String> getRoomDirection(int roomId) throws GameDataException{
//		ArrayList<String> roomDirections = new ArrayList<String>();
//		for(GameRoom room: gameRooms) {
//			if(room.getId() == roomId) {
//				roomDirections = room.getExits();
//			}
//		}
//		return roomDirections;
//	}
	/**Code gets the exit id that links to other rooms
	 * 
	 * Method: @param room
	 * Method: @param direction
	 * Method: @return
	 * Method: @throws GameDataException
	 *
	 * int
	 */
//	public int getNextRoomId(GameRoom room, String direction) throws GameDataException {
//		int result = 0;
//		for(Exit ex: room.getAllExitObject()) {
//			if(ex.getDirection().equals(direction)) {
//				result = ex.getRoomId();
//			}
//		}
//		return result;
//		
//	}
	
	/**Inform the user the room has been visited
	 * 
	 * Method: @param room
	 *
	 * void
	 */
	public void setRoomVisited(GameRoom room) {
		room.setHasVisited();
	}
	
	/**leaves item from backpack in current room
	 * 
	 * Method: @param item
	 * Method: @param room
	 *
	 * void
	 */
	public void dropItemInRoom(Item item, GameRoom room) {
		room.addItem(item);
	}
	
	/**adds an item randomly into a randomly into a room
	 *  
	 * Method: @param item
	 * Method: @param room
	 * Method: @throws GameDataException
	 *
	 * void
	 */
	public void addItemInRoom(ArrayList<Item> item, ArrayList<GameRoom> room) throws GameDataException {
		Item item1 = item.get(0);
		Item item2 = item.get(1);
		Item item3 = item.get(2);
		
		room.get(1).addItem(item1);
		room.get(2).addItem(item2);
		room.get(3).addItem(item3);
		
	}
	
	/**remove item from room after player picks it up
	 * 
	 * Method: @param item
	 * Method: @param room
	 *
	 * void
	 */
	public void removeItemFromRoom(Item item, GameRoom room) {
		room.removeItem(item);
	}
	
	/**gets item from specific room
	 * 
	 * Method: @param room
	 * Method: @return
	 *
	 * ArrayList<Item>
	 */
//	public ArrayList<Item> getItemFromRoom(GameRoom room) {
//		return room.getItems();
//	}
	
	

	
	

}
