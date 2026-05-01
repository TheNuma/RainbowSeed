package com.numa.rainbow;

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

    @Override
    public void create() {
        initializeUI();
        Item dirt1 = new Item(ItemType.DIRT);
        Item dirt2 = new Item(ItemType.DIRT);
        Item water = new Item(ItemType.WATER);
        stage.addActor(dirt1);
        stage.addActor(dirt2);
        stage.addActor(water);
        interactions = new ItemInteractions();
        combineItems(dirt1, water);
        combineItems(dirt1,dirt2);
        combineItems(water,dirt2);
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

    
    public void combineItems(Item type1, Item type2) {
    	if (interactions.hasCombinations(type1.getType(), type2.getType())) {
    		ItemType type3 = interactions.getCombination(type1.getType(), type2.getType());
    		System.out.println(type1+" and "+type2+ " combined to make "+type3);
    		Item spawnItem =new Item(type3);
    		stage.addActor(spawnItem);
    		spawnItem.setPosition((type1.getX()+type2.getX())/2f, (type1.getY()+type2.getY())/2f);
    		
    	}
    	else {
    		System.out.println("No combinations found between "+type1+ " and "+type2);
    	}
    }
}
