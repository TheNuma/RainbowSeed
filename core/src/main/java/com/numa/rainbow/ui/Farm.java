package com.numa.rainbow.ui;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.numa.rainbow.items.Combiner;
import com.numa.rainbow.items.DraggableItem;
import com.numa.rainbow.items.ItemInteractions;
import com.numa.rainbow.items.ItemType;

public class Farm {
	private final Map<ItemType,DraggableItem> allItems;
	private final ItemInteractions interactions;
	private final Stage stage;
	public Farm(Stage stage) {
		this.stage=stage;
		allItems = new HashMap<>();
		interactions =new ItemInteractions();
		UI.setItemInteractions(interactions);
		Combiner.setItemInteractions(interactions,this);
		
		makeItem(ItemType.SEED,740,320);
		makeItem(ItemType.WATER, 500, 500);
		makeItem(ItemType.DIRT,999,505);
		makeUnavailableItem(ItemType.GRASS);

		
		setUpItemPair(allItems.get(ItemType.WATER), allItems.get(ItemType.DIRT));
	}
	
	public DraggableItem getItemFromType(ItemType itemType) {
		return allItems.get(itemType);
	}
	
	private void makeItem(ItemType itemType,float x, float y) {
		DraggableItem item = UI.makeDraggableItem(itemType);
		allItems.put(itemType, item);
		item.setPosition(x,y);
		stage.addActor(item);
	}
	private void makeUnavailableItem(ItemType itemType) {
		makeItem(itemType, -100, -100);
		allItems.get(itemType).setVisible(false);		
		
	}
	private void setupAllItems() {
		
	}
	
	private void setUpItemPair(DraggableItem item1, DraggableItem item2) {
		item1.addDropTarget(item2);
		item2.addDropTarget(item1);
	}


}
