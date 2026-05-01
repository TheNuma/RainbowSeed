package com.numa.rainbow;

import java.util.HashSet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.numa.rainbow.items.Item;
import com.numa.rainbow.items.ItemInteractions;
import com.numa.rainbow.items.ItemType;
import com.numa.rainbow.ui.Farm;
import com.numa.rainbow.ui.UI;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RainbowSeedGame extends ApplicationAdapter {
	
	private Stage stage;
    ItemInteractions interactions;
    HashSet<ItemType> dirtSet =new HashSet<>();
    HashSet<ItemType> waterSet=new HashSet<>();
    HashSet<ItemType> grassSet=new HashSet<>();

    @Override
    public void create() {
        initializeUI();
 
        dirtSet.add(ItemType.GRASS);
        Item dirt1 = new Item(ItemType.DIRT, dirtSet);
//        Item dirt2 = new Item(ItemType.DIRT);
        Item water = new Item(ItemType.WATER,waterSet);
        stage.addActor(dirt1);
//        stage.addActor(dirt2);
        stage.addActor(water);
        interactions = new ItemInteractions();
        combineItems(dirt1, water);
//        combineItems(dirt1,dirt2);
//        combineItems(water,dirt2);
    }

	@Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
          
        stage.act();
        stage.getViewport().apply();
        stage.draw();
    }

	private void initializeUI() {
    	UI.initialize();

		this.stage = new Stage(new FitViewport(1280, 720));
		Gdx.input.setInputProcessor(stage);
		new Farm(stage);
	}

    
    public void combineItems(Item item1, Item item2) {
       	if (interactions.hasCombinations(item1.getType(), item2.getType())) {
    		//create new object
    		ItemType type3 = interactions.getCombination(item1.getType(), item2.getType());    
    		
    		grassSet.add(ItemType.WHEAT);
    		
    		System.out.println(item1.getType()+" and "+item2.getType()+ " combined to make "+type3);
    		Item spawnItem =new Item(type3,grassSet);
    		stage.addActor(spawnItem);
    		spawnItem.setPosition((item1.getX()+item2.getX())/2f, (item1.getY()+item2.getY())/2f);
    		item1.removeCombo(type3);
    		item2.removeCombo(type3);
    		//remove 'parent' objects if necessary
    		checkAndRemoveSingleUse(item1);
    		checkAndRemoveSingleUse(item2);
    	}
    	else {
    		System.out.println("No combinations found between "+item1+ " and "+item2);
    	}
    }
    
    public void checkAndRemoveSingleUse(Item t) {
    	if (t.getType().isSingleUse()&&!t.hasRemainingCombinations())
    	{
    		System.out.println("All combinations with "+t.getType().toString()+" has been found");
    		t.remove();
    	}
    }
    
}
