package MiniGame2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import controller.GameRoomController;
import service.GameRoomService;



@SpringBootApplication
public class MiniGame2Application {
	private static Logger LOG = LoggerFactory.getLogger(MiniGame2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(MiniGame2Application.class, args);
		
		GameRoomService service = new GameRoomService();
		GameRoomController gr = new GameRoomController(service);
		gr.ControllerStart();
	}

}
