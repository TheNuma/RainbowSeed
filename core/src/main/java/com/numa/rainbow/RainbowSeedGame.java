package com.numa.rainbow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.numa.rainbow.items.ItemInteractions;
import com.numa.rainbow.items.ItemType; 

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RainbowSeedGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    ItemInteractions interactions;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        interactions=new ItemInteractions();
       
        combineItems(ItemType.DIRT, ItemType.WATER);
        combineItems(ItemType.DIRT, ItemType.DIRT);
        combineItems(ItemType.WATER, ItemType.DIRT);



    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
    
    public void combineItems(ItemType type1, ItemType type2) {
    	if (interactions.hasCombinations(type1, type2)) {
    		ItemType type3 = interactions.getCombination(type1, type2);
    		System.out.println(type1+" and "+type2+ " combined to make "+type3);
    	}
    	else {
    		System.out.println("No combinations found between "+type1+ " and "+type2);
    	}
    }
}
