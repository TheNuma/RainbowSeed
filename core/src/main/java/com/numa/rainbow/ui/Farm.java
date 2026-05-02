package com.numa.rainbow.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.numa.rainbow.items.Combiner;
import com.numa.rainbow.items.DraggableItem;
import com.numa.rainbow.items.ItemInteractions;
import com.numa.rainbow.items.ItemType;

public class Farm {
	private final ItemInteractions interactions;
	public Farm(Stage stage) {
		interactions =new ItemInteractions();
		UI.setItemInteractions(interactions);
		Combiner.setItemInteractions(interactions);
		
		DraggableItem seeds = UI.makeDraggableItem(ItemType.SEED);
		seeds.setPosition(740, 320);
		stage.addActor(seeds);

		DraggableItem wateringCan = UI.makeDraggableItem(ItemType.WATER);
		wateringCan.setPosition(500, 500);
		stage.addActor(wateringCan);

		DraggableItem dirt = UI.makeDraggableItem(ItemType.DIRT);
		dirt.setPosition(999, 505);
		stage.addActor(dirt);
		
		setUpItemPair(wateringCan, dirt);
	}
	
	private void setUpItemPair(DraggableItem item1, DraggableItem item2) {
		item1.addDropTarget(item2);
		item2.addDropTarget(item1);
	}


}
