package com.numa.rainbow.ui;

import java.util.HashSet;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.numa.rainbow.items.ItemType;

public class Farm {
	public Farm(Stage stage) {
		DraggableItem seeds = UI.makeDraggableItem(ItemType.SEED);
		seeds.setPosition(740, 320);
		stage.addActor(seeds);

		DraggableItem wateringCan = UI.makeDraggableItem(ItemType.WATER);
		wateringCan.setPosition(500, 500);
		stage.addActor(wateringCan);

		DraggableItem dirt = UI.makeDraggableItem(ItemType.DIRT);
		dirt.setPosition(999, 600);
		stage.addActor(dirt);
		
		setUpItemPair(wateringCan, dirt);
	}
	
	private void setUpItemPair(DraggableItem item1, DraggableItem item2) {
		item1.addDropTarget(item2);
		item2.addDropTarget(item1);
	}


}
