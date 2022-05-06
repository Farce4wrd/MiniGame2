package com.minigame2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.minigame2.data.GameRoomRepository;
import com.minigame2.data.ItemRepository;
import com.minigame2.data.MonsterRepository;
import com.minigame2.exception.GameDataException;
import com.minigame2.model.GameRoom;
import com.minigame2.model.Item;
import com.minigame2.service.GameRoomService;
import com.minigame2.service.ItemService;
import com.minigame2.view.Adventure;
import com.minigame2.view.GameView;

import javafx.application.Application;






@SpringBootApplication
public class MiniGame2Application{
	private static Logger LOG = LoggerFactory.getLogger(MiniGame2Application.class);
	
	@Autowired
    private ApplicationContext appContext;

	public static void main(String[] args) throws GameDataException {
		Application.launch(Adventure.class, args);
		//ConfigurableApplicationContext context = SpringApplication.run(MiniGame2Application.class, args);
		//GameView game = context.getBean(GameView.class);
		//game.start();
		
		
		
	}
	@Bean
	CommandLineRunner commandLineRunner(ItemRepository itemRepo,GameRoomRepository roomRepo, MonsterRepository monsterRepo) {
		return args ->{
			GameRoomService gr = new GameRoomService(roomRepo);
			GameRoom games = gr.getRoom(15);
			System.out.println(games);
			//List<Item> items = gr.getWeapons();
//			ItemService gr = new ItemService(itemRepo);
//			List<Item> items = gr.getWeapons();
//			items.forEach(monster ->{
//				System.out.println(monster);
//			});
			//GameRoomService gr = new GameRoomService(roomRepo);
			//List<GameRoom> items = gr.getRooms();
			//items.forEach(monster ->{
			//	System.out.println(monster);
			//});
			
			//GameRoom room1 = null;
			//gameRoomRepository.save(room1);
//			ArrayList<GameRoom> rooms = new ArrayList<GameRoom>();
//			for (GameRoom room: gameRoomRepository.findAll()){
//				System.out.println(room);
//			}
		};
	}
	
	
	
	
	

}
