package com.numa.rainbow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.numa.rainbow.audio.RainbowAudioManager;
import com.numa.rainbow.items.Combiner;
import com.numa.rainbow.ui.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RainbowSeedGame extends ApplicationAdapter {

	public static final int WORLD_WIDTH = 1920;
	public static final int WORLD_HEIGHT = 1080;

	public static final float UI_WIDTH_FRACTION = 0.075f;

	private BackgroundStage backgroundStage;
	private Stage stage;
	private UIStage uiStage;
	private RainbowAudioManager audio;

	@Override
	public void create() {
		audio = new RainbowAudioManager();
		audio.initializeSounds();
		audio.initializeMusic();
		initializeUI();
		
		uiStage.introCutscene();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0f, 0f, 0f, 1f);
		setTooltipManagerTimes();

		backgroundStage.act();
		backgroundStage.getViewport().apply();
		backgroundStage.draw();

		stage.act();
		stage.getViewport().setScreenPosition(backgroundStage.getViewport().getScreenX(), backgroundStage.getViewport().getScreenY());
		stage.getViewport().apply();
		stage.draw();

		uiStage.act();
		uiStage.getViewport().apply();
		uiStage.draw();

		audio.update();
	}

	@Override
	public void resize(int width, int height) {
		backgroundStage.getViewport().update(width, height, true);
		stage.getViewport().update(width, height, true);
		uiStage.getViewport().update(width, height, true);
	}

	private void initializeUI() {
		UI.initialize();

		backgroundStage = new BackgroundStage(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT));

		stage = new Stage(new FitViewport((1f - UI_WIDTH_FRACTION) * WORLD_WIDTH, WORLD_HEIGHT));

		Farm farm = new Farm(stage);
		uiStage = new UIStage(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT), farm.getSeasonShifter(), stage, () -> farm.getAllColorfulItems(), audio);
		Combiner.setUIelements(uiStage);

		farm.getSeasonShifter().registerSeasonalThing(backgroundStage);
		farm.getSeasonShifter().registerSeasonalThing(audio);

		Gdx.input.setInputProcessor(new InputMultiplexer(stage, uiStage));
	}

	private void setTooltipManagerTimes() {
		TooltipManager.getInstance().initialTime = 0f;
		TooltipManager.getInstance().resetTime = Float.MAX_VALUE;
		TooltipManager.getInstance().subsequentTime = 0;
	}

}
