package com.minigame2.view;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.minigame2.view.Adventure.StageReadyEvent;

import javafx.stage.Stage;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent>{

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		// TODO Auto-generated method stub
		Stage stage = event.getStage();
		
	}

}
