package com.numa.rainbow.ui;

import static com.numa.rainbow.items.ItemType.*;

import java.util.*;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.numa.rainbow.items.*;
import com.numa.rainbow.season.SeasonShifter;

public class Farm {
	
	private final Map<ItemType,DraggableItem> allItems;
	private final ItemInteractions interactions;
	private final Stage stage;
	private final SeasonShifter seasonShifter;
	
	public Farm(Stage stage) {
		this.stage=stage;
		this.seasonShifter = new SeasonShifter();
		
		allItems = new HashMap<>();
		interactions =new ItemInteractions();
		Combiner.setItemInteractions(interactions,this::getItemFromType);
		
		makeItem(SPRINGSEED,740,320);
		makeItem(WATER, 500, 500);
		makeItem(DIRT,999,505);		
		makeItem(WEED,233,111);
		makeUnavailableItem(GRASS);	
		makeUnavailableItem(SUMMERSEED);
		DraggableItem itemTemp=allItems.get(SUMMERSEED);
		itemTemp.setPosition(MathUtils.random(stage.getWidth()-itemTemp.getWidth()), MathUtils.random(stage.getHeight()-itemTemp.getHeight()));
		makeUnavailableItem(ROCK);
		itemTemp=allItems.get(ROCK);
		itemTemp.setPosition(MathUtils.random(stage.getWidth()-itemTemp.getWidth()), MathUtils.random(stage.getHeight()-itemTemp.getHeight()));		
		makeUnavailableItem(SUN);
		itemTemp=allItems.get(SUN);
		itemTemp.setPosition(MathUtils.random(stage.getWidth()-itemTemp.getWidth()), MathUtils.random(stage.getHeight()-itemTemp.getHeight()));
		makeUnavailableItem(SEAWATER);
		makeUnavailableItem(STRAWBERRY);
		makeUnavailableItem(FALLSEED);
		itemTemp= allItems.get(FALLSEED);
		itemTemp.setPosition(MathUtils.random(stage.getWidth()-itemTemp.getWidth()), MathUtils.random(stage.getHeight()-itemTemp.getHeight()));
		makeUnavailableItem(BAMBOO);
		makeUnavailableItem(BUSH);
		makeUnavailableItem(WHEAT);
		makeUnavailableItem(SCARECROW);
		itemTemp=allItems.get(SCARECROW);
		itemTemp.setPosition(MathUtils.random(stage.getWidth()-itemTemp.getWidth()), MathUtils.random(stage.getHeight()-itemTemp.getHeight()));
		makeUnavailableItem(TREE);
		makeUnavailableItem(AXE);
		makeUnavailableItem(STICK);
		makeUnavailableItem(TULIP);
		makeUnavailableItem(BERRYBUSH);
		makeUnavailableItem(VINE);
		makeUnavailableItem(BRAMBLES);
		makeUnavailableItem(PUMPKIN);
		makeUnavailableItem(SNOWDROP);
		makeUnavailableItem(SNOW);
		itemTemp=allItems.get(SNOW);
		itemTemp.setPosition(MathUtils.random(stage.getWidth()-itemTemp.getWidth()), MathUtils.random(stage.getHeight()-itemTemp.getHeight()));
		makeUnavailableItem(WINTERSEED);
		itemTemp=allItems.get(WINTERSEED);
		itemTemp.setPosition(MathUtils.random(stage.getWidth()-itemTemp.getWidth()), MathUtils.random(stage.getHeight()-itemTemp.getHeight()));
		makeUnavailableItem(DANDELION);
		makeUnavailableItem(REED);
		makeUnavailableItem(FLAX);
		makeUnavailableItem(HOPS);	
		makeUnavailableItem(SEAWEED);
		makeUnavailableItem(CACTUS);
		makeUnavailableItem(PLUMTREE);
		makeUnavailableItem(ROSE);
		makeUnavailableItem(ORANGE_TREE);
		makeUnavailableItem(DAFFODIL);
		makeUnavailableItem(GREENBEANS);
		makeUnavailableItem(BLUEBERRY);
		makeUnavailableItem(INDIGO);
		makeUnavailableItem(VIOLET);
		makeUnavailableItem(RAINBOWSEED);		
		for(int i=0;i<ItemType.values().length;i++) {
			if(!allItems.containsKey(ItemType.values()[i])) {
				System.out.println(ItemType.values()[i]);	
			}
		}
		
		setupAllItems();
	}

	private DraggableItem getItemFromType(ItemType itemType) {
		return allItems.get(itemType);
	}
	
	public List<DraggableItem> getAllColorfulItems() {
		return List.of( 
				allItems.get(ItemType.ROSE),
				allItems.get(ItemType.ORANGE_TREE),
				allItems.get(ItemType.DAFFODIL),
				allItems.get(ItemType.GREENBEANS),
				allItems.get(ItemType.BLUEBERRY),
				allItems.get(ItemType.INDIGO),
				allItems.get(ItemType.VIOLET)
				);
	}
	
	private void makeItem(ItemType itemType,float x, float y) {
		DraggableItem item = UI.makeDraggableItem(itemType,interactions.getCombinationsFor(itemType), seasonShifter);
		allItems.put(itemType, item);
		item.setPosition(x,y);
		stage.addActor(item);
		seasonShifter.registerSeasonalThing(item); 
	}
	private void makeUnavailableItem(ItemType itemType) {
		makeItem(itemType, -100, -100);
		allItems.get(itemType).setVisible(false);		
		
	}
	private void setupAllItems() {
		Set<CombinationKey> combinations=interactions.getAllCombinations();
		for (CombinationKey combo:combinations) {		
			setUpItemPair(getItemFromType(combo.getType1()) , getItemFromType(combo.getType2()));
		}
	}
	
	private void setUpItemPair(DraggableItem item1, DraggableItem item2) {
		item1.addDropTarget(item2);
		item2.addDropTarget(item1);
	} 

	public SeasonShifter getSeasonShifter() {
		return seasonShifter;
	}

}
