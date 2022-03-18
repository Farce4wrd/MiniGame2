package exception;

import java.io.IOException;

public class GameDataException extends IOException {
	
	public GameDataException() {
		super();
	}
	
	public GameDataException(String message) {
		super(message);
	}

}
