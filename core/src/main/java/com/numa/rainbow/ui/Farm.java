package com.numa.rainbow.ui;

import static com.numa.rainbow.items.ItemType.AXE;
import static com.numa.rainbow.items.ItemType.BAMBOO;
import static com.numa.rainbow.items.ItemType.BERRYBUSH;
import static com.numa.rainbow.items.ItemType.BLUEBERRY;
import static com.numa.rainbow.items.ItemType.BRAMBLES;
import static com.numa.rainbow.items.ItemType.BUSH;
import static com.numa.rainbow.items.ItemType.CACTUS;
import static com.numa.rainbow.items.ItemType.DAFFODIL;
import static com.numa.rainbow.items.ItemType.DANDELION;
import static com.numa.rainbow.items.ItemType.DIRT;
import static com.numa.rainbow.items.ItemType.FALLSEED;
import static com.numa.rainbow.items.ItemType.FLAX;
import static com.numa.rainbow.items.ItemType.GRASS;
import static com.numa.rainbow.items.ItemType.GREENBEANS;
import static com.numa.rainbow.items.ItemType.HOPS;
import static com.numa.rainbow.items.ItemType.INDIGO;
import static com.numa.rainbow.items.ItemType.ORANGE_TREE;
import static com.numa.rainbow.items.ItemType.PLUMTREE;
import static com.numa.rainbow.items.ItemType.PUMPKIN;
import static com.numa.rainbow.items.ItemType.RAINBOWSEED;
import static com.numa.rainbow.items.ItemType.REED;
import static com.numa.rainbow.items.ItemType.ROCK;
import static com.numa.rainbow.items.ItemType.ROSE;
import static com.numa.rainbow.items.ItemType.SCARECROW;
import static com.numa.rainbow.items.ItemType.SEAWATER;
import static com.numa.rainbow.items.ItemType.SEAWEED;
import static com.numa.rainbow.items.ItemType.SNOW;
import static com.numa.rainbow.items.ItemType.SNOWDROP;
import static com.numa.rainbow.items.ItemType.SPRINGSEED;
import static com.numa.rainbow.items.ItemType.STICK;
import static com.numa.rainbow.items.ItemType.STRAWBERRY;
import static com.numa.rainbow.items.ItemType.SUMMERSEED;
import static com.numa.rainbow.items.ItemType.SUN;
import static com.numa.rainbow.items.ItemType.TREE;
import static com.numa.rainbow.items.ItemType.TULIP;
import static com.numa.rainbow.items.ItemType.VINE;
import static com.numa.rainbow.items.ItemType.VIOLET;
import static com.numa.rainbow.items.ItemType.WATER;
import static com.numa.rainbow.items.ItemType.WEED;
import static com.numa.rainbow.items.ItemType.WHEAT;
import static com.numa.rainbow.items.ItemType.WINTERSEED;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.numa.rainbow.items.CombinationKey;
import com.numa.rainbow.items.Combiner;
import com.numa.rainbow.items.DraggableItem;
import com.numa.rainbow.items.ItemInteractions;
import com.numa.rainbow.items.ItemType;
import com.numa.rainbow.season.SeasonShifter;

public class Farm {
	
	private final Map<ItemType,DraggableItem> allItems;
	private final ItemInteractions interactions;
	private final Stage stage;
	private final SeasonShifter seasonShifter;
	
	public Farm(Stage stage) {
		this.stage=stage;
		this.seasonShifter = new SeasonShifter();
		UI.setSeasonShifter(seasonShifter);
		
		allItems = new HashMap<>();
		interactions =new ItemInteractions();
		Combiner.setItemInteractions(interactions,this::getItemFromType);
		
		makeItem(SPRINGSEED,740,320);
		makeItem(WATER, 500, 500);
		makeItem(DIRT,999,505);		
		makeItem(WEED,233,111);
		makeUnavailableItem(GRASS);	
		makeUnavailableItem(SUMMERSEED);
		allItems.get(SUMMERSEED).setPosition(600, 55);
		makeUnavailableItem(ROCK);
		makeUnavailableItem(SUN);	
		makeUnavailableItem(SEAWATER);
		makeUnavailableItem(STRAWBERRY);
		makeUnavailableItem(FALLSEED);
		makeUnavailableItem(BAMBOO);
		makeUnavailableItem(BUSH);
		makeUnavailableItem(WHEAT);
		makeUnavailableItem(SCARECROW);
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
		makeUnavailableItem(WINTERSEED);
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
	
	private void makeItem(ItemType itemType,float x, float y) {
		DraggableItem item = UI.makeDraggableItem(itemType,interactions.getCombinationsFor(itemType));
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
