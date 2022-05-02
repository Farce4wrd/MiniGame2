package com.minigame2;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.minigame2.data.GameRoomRepository;
import com.minigame2.exception.GameDataException;
import com.minigame2.model.GameRoom;
import com.minigame2.view.GameView;




@SpringBootApplication
public class MiniGame2Application{
	private static Logger LOG = LoggerFactory.getLogger(MiniGame2Application.class);
	
	@Autowired
    private ApplicationContext appContext;

	public static void main(String[] args) throws GameDataException {
		ConfigurableApplicationContext context = SpringApplication.run(MiniGame2Application.class, args);
		GameView game = context.getBean(GameView.class);
		game.start();
		
		
		
	}
	@Bean
	CommandLineRunner commandLineRunner(GameRoomRepository gameRoomRepository) {
		return args ->{
			CreateInsert cr = new CreateInsert();
			cr.createQuery();
//			GameRoom room1 = new GameRoom("Peter", "coding", true, null, null);
//			gameRoomRepository.save(room1);
//			ArrayList<GameRoom> rooms = new ArrayList<GameRoom>();
//			for (GameRoom room: gameRoomRepository.findAll()){
//				System.out.println(room);
//			}
		};
	}
	
	
	

}
