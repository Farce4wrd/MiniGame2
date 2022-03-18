package com.minigame2;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.minigame2.controller.GameRoomController;
import com.minigame2.exception.GameDataException;
import com.minigame2.service.GameRoomService;
import com.minigame2.view.GameView;




@SpringBootApplication
public class MiniGame2Application{
	private static Logger LOG = LoggerFactory.getLogger(MiniGame2Application.class);
	
	@Autowired
    private ApplicationContext appContext;

	public static void main(String[] args) throws GameDataException {
		ConfigurableApplicationContext context = SpringApplication.run(MiniGame2Application.class, args);
		GameRoomService service = new GameRoomService();
		//GameRoomController gr = new GameRoomController();
		GameView game = context.getBean(GameView.class);
		game.start();
		
		//System.out.println(context.);
		//var game = context.getBean("GameView", GameView.class);
		//game.start();
		
//		GameRoomService service = new GameRoomService();
//		GameRoomController gr = new GameRoomController(service);
//		gr.ControllerStart();
		
	}
	
	

}
