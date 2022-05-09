package com.minigame2;

import com.minigame2.data.CharacterRepository;
import com.minigame2.data.GameRoomRepository;
import com.minigame2.data.ItemRepository;
import com.minigame2.data.MonsterRepository;
import com.minigame2.exception.GameDataException;
import com.minigame2.model.Character;
import com.minigame2.model.GameRoom;
import com.minigame2.model.Item;
import com.minigame2.service.CharacterService;
import com.minigame2.service.GameRoomService;
import com.minigame2.service.ItemService;
import com.minigame2.view.Adventure;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;






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
	CommandLineRunner commandLineRunner(ItemRepository itemRepo,GameRoomRepository roomRepo, MonsterRepository monsterRepo, CharacterRepository characterRepo) {
		return args ->{
			
			
			
//			GameRoomService gr = new GameRoomService(roomRepo);
//			ItemService itemService = new ItemService(itemRepo);
//			CharacterService characterService = new CharacterService(characterRepo);
//			//GameRoom games = gr.getRoom(16);
//			GameRoom location = gr.getRoomWithItem(1);
//			Character chara = new Character("Josh",location,50,3,1,1);
//			List<Item> characterInventory = chara.getInventory();
//			String item ="bandage";
//			
//			List<Item> roomInventory = itemService.getItemsById(location);
//			for(Item itemToFind : roomInventory) {
//				if(itemToFind.getName().equalsIgnoreCase(item)) {
//					characterInventory.add(itemToFind);  //add item to character's inventory
//					chara.setInventory(characterInventory);
//					
//					location.getItems().remove(itemToFind); //removes room item from specific room
//					location.getItems().forEach(it->{
//						System.out.println(it);
//						System.out.println();
//					});
//					characterService.characterSave(chara); //updates the character in the db with new item
//					
//					gr.addRoom(location);  //saves state of the room to db
//					String res =itemToFind.getName() + " has been added to your inventory!\n";
//					System.out.println(res + "has been added to inventory");
//				}
//			}
//			//GameRoom roo = gr.getRoomWithItem(16);
//			List<GameRoom> rooms =gr.getRoomWItems();
//			rooms.forEach(roo->{
//				System.out.println(roo.getName()+"\n"+roo.getItems());
//			});
//			System.out.println(chara);
//			System.out.println(gr.getRoomWithItem(1));
			//characterService.characterSave(chara); 
			
//			System.out.println(games);
			//List<Item> items = gr.getWeapons();
//			ItemService gri = new ItemService(itemRepo);
//			List<Item> items = gri.getItemsById(games);
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
//			}pick-up:
		};
	}
	
	
	
	
	

}
