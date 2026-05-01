package com.numa.rainbow.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Farm {
	
	private final Stage stage;

	public Farm(Stage stage) {
		this.stage = stage;
		
//		stage.addActor(UI.makeTextButton("Test Button", () -> System.out.println("CLICKED")));

		DraggableItem seeds = UI.makeDraggableItem("sesame", "Seeds");
//		seeds.setPosition(500, 500);
		stage.addActor(seeds);
		
		DraggableItem wateringCan = UI.makeDraggableItem("watering-can", "Watering Can");
		seeds.setPosition(500, 500);
		stage.addActor(wateringCan);
		
		seeds.addDropTarget(wateringCan);
		wateringCan.addDropTarget(seeds);
	}


	
}
