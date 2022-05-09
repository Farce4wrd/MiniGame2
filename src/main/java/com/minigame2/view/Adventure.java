package com.minigame2.view;

import com.minigame2.MiniGame2Application;
import com.minigame2.controller.GameRoomController;
import com.minigame2.model.Character;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Adventure extends Application {
	
	@Autowired
	private ConfigurableApplicationContext applicationContext;
	private TextArea output = new TextArea();
	private TextField input = new TextField();
	private Map<String, Command> commandList = new HashMap<>();
	private String pickupItem;
	private String direction;
	GameRoomController grc;
	
	//This is when the stage is created
	public Parent createContent() {
		output.setPrefHeight(500);
		output.setFont(Font.font(20));
		output.setEditable(false);  //-> Makes sure user cannot edit output
		output.setFocusTraversable(false);;  //->Makes sure the input is highlighted instead of output on instantiation
		
		VBox root = new VBox(15, output, input);
		root.setPadding(new Insets(15));
		root.setPrefSize(800,600);

		initGame();
		userInput();

		return root;
	}
	public static Character chara;
	
	private void initGame() {
		println("Welcome to Scary Place");
		chara = grc.createCharacterAtBeginning("Josh");
		initCommands();
	}

	private void userInput() {
		println("__________________________________________________________");
		//always check for your inputs
		input.setOnAction(e ->{
			String inputText = input.getText();
			input.clear();
			onInput(inputText);
			println("__________________________________________________________");
		});
	}
	private void initCommands() {
		commandList.put("close", new Command("Close -", "Closes the program.", Platform::exit ));
		commandList.put("help", new Command("Help -", "Display all user commands.", this::runHelp));
		commandList.put("showexample", new Command("Show Example -", "hope this works.", this::showCommand));
		commandList.put("seeexits", new Command("See Exits -", "Show list of exits.", this.grc::showExits));
		commandList.put("go", new Command("Go -", "Moves to EAST or WEST or NORTH or SOUTH (example: go > east).", () ->moveCommand(direction)));
		commandList.put("pickup", new Command("Pick Up -", "Picks up any item (example: pickup > bandage).", () ->pick(pickupItem)));
		commandList.put("status", new Command("Status", "Shows the room you're currently in, your HP and inventory", () -> showLocation(chara)));
		commandList.put("look", new Command("Show", "Shows items in the room", () -> showItemsInRoom(chara)));
		//Need a method to show room currently in, list of items in room, exits in the room.
		//May also display status of character in the method above (hp, inventory, e.t.c)
		/*Made the status and look commands, the exit command still prints to the console1
		 * 
		 */
		
	}
	//Method that verifies the input
	private void onInput(String inputText) {
		String rawInput = inputText.replaceAll("\\s", "");
		String[] query = rawInput.split(">", 2);
		String command = query[0].toLowerCase();

		if(!commandList.containsKey(command)) {
			println("Command "+ command + " not recognized");
			return;
		} else if(command.equals("pickup")) {
			pickupItem = query[1].toLowerCase();
		}else if(command.equals("go")) {
			direction = query[1].toLowerCase();
		}

		commandList.get(command).execute();
	}
	
	//This prints all your show message to the Screen to be visible to user
	private void println(String line) {
		output.appendText(">"+line + "\n");
		output.setWrapText(true);
	}
	
	private void showLocation(Character chara)
	{
		println(grc.currentLocation(chara));
	}
	
	private void showItemsInRoom(Character chara)
	{
		println(grc.look(chara));
	}
	
	private void showCommand()
	{
		String holdtheline = grc.showExits();
		println(holdtheline);
	}
	
	public static String a = "li";
	
	private void pick(String item) {
		Character chara = grc.createCharacterAtBeginning("Josh");
		String result = grc.pickup(chara, item);
		println(result);
	}
	
	private void moveCommand(String direction) {
		String result = grc.move(chara, direction);
		println(result);
	}

	private void runHelp() {
		commandList.forEach((name, command) ->{
			println(command.getName()+ " " +command.getDescription());
		});
	}
	
	
	public void changeScene() {
		//stg.getScene().setRoot(createContent());
	}
	
	
	@Override
	public void init() {
		this.applicationContext= new SpringApplicationBuilder(MiniGame2Application.class).run();
		grc = this.applicationContext.getBean(GameRoomController.class);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationContext.publishEvent(new StageReadyEvent(primaryStage));
		//stg = primaryStage;
		//Parent root =FXMLLoader.load(getClass().getResource("Adventure.fxml"));
		//primaryStage.setTitle("Login Screen");
		//primaryStage.setScene(new Scene(root,500,400));
		primaryStage.setTitle("Scary Place");
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		
	}
	@Override
	public void stop() {
		applicationContext.close();
		Platform.exit();
	}
	static class StageReadyEvent extends ApplicationEvent {
		public StageReadyEvent(Stage stage) {
			super(stage);
		}
		public Stage getStage() {
			return ((Stage) getSource());
		}
	}
}
