package com.numa.rainbow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.numa.rainbow.audio.RainbowAudioManager;
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
		RainbowAudioManager.initializeMusic();
		RainbowAudioManager.playSong(RainbowAudioManager.springSong);

		interactions = new ItemInteractions();
		combineItems(ItemType.DIRT, ItemType.WATER);
		combineItems(ItemType.DIRT, ItemType.DIRT);
		combineItems(ItemType.WATER, ItemType.DIRT);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

		stage.act();
		stage.getViewport().apply();
		stage.draw();

		RainbowAudioManager.update();
	}

	private void initializeUI() {
		UI.initialize();

		stage = new Stage(new FitViewport(1280, 720));
		Gdx.input.setInputProcessor(stage);
		new Farm(stage);
	}

	public void combineItems(ItemType type1, ItemType type2) {
		if (interactions.hasCombinations(type1, type2)) {
			ItemType type3 = interactions.getCombination(type1, type2);
			System.out.println(type1 + " and " + type2 + " combined to make " + type3);
		} else {
			System.out.println("No combinations found between " + type1 + " and " + type2);
		}
	}
}
