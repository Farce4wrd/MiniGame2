package MiniGame2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MiniGame2Application {
	private static Logger LOG = LoggerFactory.getLogger(MiniGame2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(MiniGame2Application.class, args);
	}

}
