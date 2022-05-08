package com.minigame2.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import com.minigame2.MiniGame2Application;
import com.minigame2.controller.GameRoomController;
import com.minigame2.data.GameRoomRepository;
import com.minigame2.model.Character;
import com.minigame2.model.Player;
import com.minigame2.service.GameRoomService;
import com.minigame2.service.ItemService;
import com.minigame2.service.MonsterService;

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

@Component
public class Adventure extends Application {
	
	@Autowired
	private ConfigurableApplicationContext applicationContext;
	private TextArea output = new TextArea();
	private TextField input = new TextField();
	private Map<String, Command> commands = new HashMap<>();
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
	}

	private void userInput() {
		//always check for your inputs
		input.setOnAction(e ->{
			String inputText = input.getText();
			input.clear();
			onInput(inputText);
		});
	}

	//Method that verifies the input
	private void onInput(String inputText) {
		String rawInput = inputText.replaceAll("\\s", "");
		String[] query = rawInput.split("-", 2);
		String command = query[0];
		if(command.equals("pickup")) {
			String item = query[1];
			input.setText(item);
		}

		initCommands(command);
//		if(!commands.containsKey(command)) {
//			println("Command "+ command + " not recognized");
//			return;
//		}
//
//		commands.get(command).execute();
	}

	private void initCommands(String command) {
		switch (command.toUpperCase()) {
			case "EXIT" -> new Command("exit", "Closes the program", Platform::exit).execute();
			case "HELP" -> new Command("help", "Display all user commands", this::runHelp).execute();
			case "SHOW" -> new Command("show example text", "hope this works", this::showCommand).execute();
			case "SEE" -> new Command("see", "see exit list", this.grc::tester).execute();
			case "EAST" -> new Command("EAST", "Move east", () -> moveCommand("EAST")).execute();
			case "NORTH" -> new Command("NORTH", "Move east", () -> moveCommand("NORTH")).execute();
			case "WEST" -> new Command("WEST", "Move east", () -> moveCommand("WEST")).execute();
			case "SOUTH" -> new Command("SOUTH", "Move east", () -> moveCommand("SOUTH")).execute();
			case "PICKUP" -> new Command("pickup", "picks up any item", () -> pick(input.getText())).execute();
			default -> {
				println("Command "+ command + " not recognized");
			}
		}
	}
	
	//This prints all your show message to the Screen to be visible to user
	private void println(String line) {
		output.appendText(">"+line + "\n");
	}
	
	private void showCommand()
	{
		String holdtheline = grc.tester();
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
		println("EXIT - Closes the program");
		println("HELP - Display all user commands");
		println("SHOW - Hope this works");
		println("SEE - See exit list");
		println("GO: Moves to EAST or WEST or NORTH or SOUTH (example: go-east).");
		println("PICKUP - Picks up any item (example: pickup-bandage)");
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
