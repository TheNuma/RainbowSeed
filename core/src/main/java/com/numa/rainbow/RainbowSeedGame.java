package com.numa.rainbow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.numa.rainbow.audio.RainbowAudioManager;
import com.numa.rainbow.ui.Farm;
import com.numa.rainbow.ui.UI;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RainbowSeedGame extends ApplicationAdapter {
	
	private Stage stage;
    
	@Override
	public void create() {
		initializeUI();
		RainbowAudioManager.initializeMusic();
		RainbowAudioManager.playSong(RainbowAudioManager.springSong);

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

}
