package com.minigame2.view;

import java.util.HashMap;
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
//	
//	private GameRoomController grc;
//	@Autowired
//	public Adventure(GameRoomController grc)
//	{
//		this.grc = grc;
//	}
//	
//	public Adventure()
//	{
//		
//	}
	
	@Autowired
	private ConfigurableApplicationContext applicationContext;
	
	//GameRoomController grc = applicationContext.getBean(GameRoomController.class);
	
	private TextArea output = new TextArea();
	private TextField input = new TextField();
	
	//Stores all the user commands.
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
	
	//This prints all your mshowessage to the Screen to be visible to user
	private void println(String line) {
		output.appendText(">"+line + "\n");
	}
	
	private void showCommand()
	{
		String holdtheline = grc.tester();
		println(holdtheline);
	}
	
	//Method that responds to your inputs
	private void onInput(String line) {
		if(!commands.containsKey(line)) {
			println("Command "+ line+ " not recognized");
			return;
		}
		commands.get(line).execute();
	}
	
	//ItemService itemService = new ItemService(itemRepository);
	
	//GameRoomController grc = applicationContext.getBean(GameRoomController.class);
	
	private void initCommands() {
		commands.put("exit", new Command("exit", "Closes the program", Platform::exit ));
		commands.put("help", new Command("help", "Display all user commands", this::runHelp));
		commands.put("show", new Command("show example text", "hope this works", this::showCommand));
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
