package com.numa.rainbow.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.numa.rainbow.items.Item;
import com.numa.rainbow.items.ItemType;

public class Farm {
	
	private final Stage stage;

	public Farm(Stage stage) {
		this.stage = stage;
		
		stage.addActor(UI.makeTextButton("Test Button", () -> System.out.println("CLICKED")));
		
		DraggableItem seeds = new DraggableItem();
		seeds.setPosition(500, 500);
		stage.addActor(seeds);
		
		

	}


	
}
