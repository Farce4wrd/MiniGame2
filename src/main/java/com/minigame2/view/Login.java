package view;

import java.io.IOException;
import view.Adventure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

	public Login() {
		
	}
	
	@FXML
	private Button button;
	@FXML
	private Label notifier;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	
	public void userLogIn(ActionEvent event) throws IOException {
		checkLogin();
	}
	
	private void checkLogin() throws IOException{
		//Adventure adv = new Adventure();
		if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
			notifier.setText("Success!");
			//adv.changeScene();
		}else if(username.getText().isEmpty()&&password.getText().isEmpty()) {
			notifier.setText("Please enter your data: ");
		}else {
			notifier.setText("wrong username/password");
		}
		
	}
	
}
