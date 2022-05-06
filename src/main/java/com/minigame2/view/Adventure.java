package com.minigame2.view;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//import service.CharacterDAOImpl;


public class Adventure extends Application {
	//private static Stage stg;
	//private static CharacterDAOImpl chara;
	
	TextArea output = new TextArea();
	TextField input = new TextField();
	
	//Stores all the user commands.
	private Map<String, Command> commands = new HashMap<>();
	
	//This is when the stage is created
	public Parent createContent() {
		output.setPrefHeight(500);
		output.setFont(Font.font(20));
		output.setEditable(false);  //-> Makes sure user cannot edit output
		
		output.setFocusTraversable(false);;  //->Makes sure the input is highlighted instead of output on instantiation
		
		VBox root = new VBox(15, output, input);
		root.setPadding(new Insets(15));
		root.setPrefSize(800,600);
		
		//always check for your inputs
		input.setOnAction(e ->{
			String inputText = input.getText();
			input.clear();
			onInput(inputText);
		});
		initGame();
		
		return root;
	}
	
	
	private void initGame() {
		println("Welcome to Scary Place");
		initCommands();
	}
	
	//This prints all your message to the Screen to be visible to user
	private void println(String line) {
		output.appendText(">"+line + "\n");
	}
	
	//Method that responds to your inputs
	private void onInput(String line) {
		if(!commands.containsKey(line)) {
			println("Command "+ line+ " not recognized");
			return;
		}
		commands.get(line).execute();
	}
	
	private void initCommands() {
		commands.put("exit", new Command("exit", "Closes the program", Platform::exit ));
		commands.put("help", new Command("help", "Display all user commands", this::runHelp));
		//commands.put("pick", new Command("pick", "Picks up an item", gameController));
	}

	private void runHelp() {
		commands.forEach((name, cmd) ->{
			println(name+"\t"+cmd.getDescription());
		});
	}
	
	public void changeScene() {
		//stg.getScene().setRoot(createContent());
	}
	
	

	public static void main(String[] args) throws SQLException {
		 //chara = new CharacterDAOImpl();
		Application.launch(Adventure.class, args);
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//stg = primaryStage;
		//Parent root =FXMLLoader.load(getClass().getResource("Adventure.fxml"));
		//primaryStage.setTitle("Login Screen");
		//primaryStage.setScene(new Scene(root,500,400));
		primaryStage.setTitle("Scary Place");
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		
	}

}
