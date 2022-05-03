package com.minigame2.view;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minigame2.controller.GameRoomController;
import com.minigame2.exception.GameDataException;
import com.minigame2.model.GameRoom;

@Component
public class GameView {
	
	private GameRoomController gameRoomController;
	
	public static int currRoomId;
	private ArrayList<String> userOptions;
	private boolean playerIsRight;
	
	@Autowired
	public GameView(GameRoomController controller) {
		this.gameRoomController = controller;
	}

	
	public void start() throws GameDataException{
		
		boolean isRunning = true;
		boolean firstLoad = true;
		Scanner userInput = new Scanner(System.in);
		//userInput.useDelimiter(System.lineSeparator());
		
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
			String player = userInput.nextLine();
			

			if(player.equals("stop")) {
				System.out.println("Are you sure you want to exit? Type 'yes' to continue");
				if(userInput.next().equals("yes")) {
					isRunning = false; //game ends.
				}else {
					System.out.println("Type start to continue: ");
				}
			}else if(player.equalsIgnoreCase("start")){
				
				

				
				/**loop to look through game files for that id and print it
				 * 
				 */

				while(currRoomId != 7) {
					for(GameRoom room: gameRoomController.getAllRooms()) { //list of rooms
						if(room.getId() == currRoomId) {
							System.out.println(room);
							//gameRoomController.getItem(room);
							gameRoomController.setRoomVisit(room);
							
							
							userOptions = gameRoomController.getRoomDirections(currRoomId);

							/**HotFix solution to make sure game ends when user reaches perfect destination.
							 * 
							 */
							if(currRoomId == 7) {
								System.out.println("You've reached the end! Congrats!");
								System.exit(0);
							}
							
							/**Tells the user what direction they can go in
							 * 
							 */
							System.out.println("You can go "+ userOptions.toString());
							System.out.println("What do you want to do? :");
							String playerChoice = userInput.nextLine();
							Boolean doOtherThingsInRoom = gameRoomController.verify(room, playerChoice);
							
							//loop to make sure the player can do multiple things in room
							while(doOtherThingsInRoom == true) {
								System.out.println("What else do you want to do? :");
								playerChoice = userInput.nextLine();
								doOtherThingsInRoom = gameRoomController.verify(room, playerChoice);
							}
							
							//logic processing their answer
							for(String options: userOptions) {
								if(playerChoice.equalsIgnoreCase(options)) { //West == West
									playerIsRight= true;
									currRoomId = gameRoomController.getRoomID(room, options);
								
								}
							}
							if(playerIsRight == false) {
								System.out.println("Wrong Input. Please try again.");
							}
							

						}

					}
				}
				


			}else {
				System.out.println("Wrong Input. Please try again.");
			}
	




		}

//

	}
	}
	
	


