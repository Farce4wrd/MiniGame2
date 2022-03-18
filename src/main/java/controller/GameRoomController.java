package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import exception.GameDataException;
import model.Exit;
import model.GameRoom;
import service.GameRoomService;

@Controller
public class GameRoomController {
	
	private GameRoomService gameRoomService;
	@Autowired
	public GameRoomController(GameRoomService gameRoomService) {
		this.gameRoomService= gameRoomService;
	}
	
	public void ControllerStart() {
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
				gameRoomService.createRoom(id, name, description, false, null, exitList);
				
			}

	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cant find game files.");
		}
		//gameRoomService.listAllRooms();
	}
	
	//Provides the exit directions in a string arraylist for view component to hold user options
	public ArrayList<String> getRoomDirections(int id) throws GameDataException{
		return gameRoomService.getRoomDirection(id);
	}
	
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
}
