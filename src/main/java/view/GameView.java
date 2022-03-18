package view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import controller.GameRoomController;
import model.Exit;
import model.GameRoom;

public class GameView {
	
	@Autowired
	private GameRoomController gameRoomController;
	public static int currRoomId;
	private ArrayList<String> userOptions;
	private boolean playerIsRight;
	
	public GameView(){
		
		System.out.println("Welcome to my adventure game. Find the way out or just explore the rooms");
		System.out.println("You can navigate the room by typing out the direction (e.g North, West, etc.)");
		System.out.println("Type start to continue, else type stop: ");
		boolean isRunning = true;
		boolean firstLoad = true;
		Scanner userInput = new Scanner(System.in);
		
		//Game loading begins
		while(isRunning) {
			currRoomId  = 1;  //To always start at the beginning
			//Purpose: To load once only when the game is ran for the first time.
			if(firstLoad == true) {
				System.out.println("Welcome to my adventure game. Find the way out or just explore the rooms");
				System.out.println("You can navigate the room by typing out the direction (e.g North, West, etc.)");
				System.out.println("Type start to continue, else type stop: ");
				firstLoad= false;
			}
			String player = userInput.next();
			

			if(player.equals("stop")) {
				System.out.println("Are you sure you want to exit? Type 'yes' to continue");
				if(userInput.next().equals("yes")) {
					isRunning = false; //game ends.
				}else {
					System.out.println("Type start to continue: ");
				}
			}else if(player.equalsIgnoreCase("start")){
				
				//loop to look through game files for that id and print it
				while(currRoomId != 7) {
					for(GameRoom room: gameRoomController.getAllRooms()) { //list of rooms
						if(room.getId() == currRoomId) {
							System.out.println(room);
							room.setHasVisited();
							ArrayList<Exit> exit= room.getExit();
							for(Exit ex: exit) {
								userOptions.add(ex.getDirection());
							}
							userOptions = gameRoomController.getRoomDirections(currRoomId);

							//HotFix solution to make sure game ends when user reaches perfect destination.
							if(currRoomId == 7) {
								System.out.println("You've reached the end! Congrats!");
								System.exit(0);
							}
							
							//Tells the user what direction they can go in
							System.out.println("You can go "+ userOptions.toString());
							System.out.println("Where do you want to go? :");
							String playerChoice = userInput.next();
							
							//logic processing their answer
							Set<String> oldUserOptions = new HashSet<>(); //used to remove old options
							for(String options: userOptions) {
								if(playerChoice.equalsIgnoreCase(options)) { //West == West
									playerIsRight= true;
									Exit temp = room.getAnExit(options); //stores the Exit object that has direction and id
									currRoomId = temp.getRoomId();
									oldUserOptions.addAll(userOptions);
//									
//											
//								
								}
							}
							if(playerIsRight == false) {
								System.out.println("Error. Please try again.");
							}
							userOptions.removeAll(oldUserOptions);  //here we update the user options (e.g North, West becoming South, East)
							oldUserOptions.clear(); 
							

						}

					}
				}
				


			}else {
				System.out.println("Wrong Input. Please try again.");
			}
	




		}



	}
	}
	
	

}
