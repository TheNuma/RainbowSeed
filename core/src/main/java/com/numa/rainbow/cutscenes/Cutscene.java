package com.numa.rainbow.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.ui.BackgroundStage;
import com.numa.rainbow.ui.UI;

public abstract class Cutscene extends Group {

	public static final float SPEECH_BUBBLE_X = RainbowSeedGame.WORLD_WIDTH * 0.3f;
	public static final float SPEECH_BUBBLE_Y = RainbowSeedGame.WORLD_WIDTH * 0.35f;
	public static final float NEXT_BUTTON_X = SPEECH_BUBBLE_X * 1.8f;
	public static final float NEXT_BUTTON_Y = SPEECH_BUBBLE_Y * 0.95f;
	

	protected Button nextButton;
	protected Actor currentBubble;
	protected Runnable endCutscene;
	
	protected Image witch;
	protected Actor darkScreen;

	protected Cutscene(Runnable endCutscene) {
		this.endCutscene = () -> {
			endCutscene.run();
			remove();
		};
		
		darkScreen = BackgroundStage.makeColoredBackground(new Color(0,0,0,0.5f), RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		darkScreen.setSize(RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		darkScreen.setColor(0, 0, 0, 0);
		darkScreen.addAction(Actions.fadeIn(0.5f));
		addActor(darkScreen);
		
		Texture tex = new Texture(Gdx.files.internal("ui/witch.png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		witch = new Image(tex);
		witch.setX(RainbowSeedGame.WORLD_WIDTH*0.02f);
		witch.setColor(1, 1, 1, 0);
		witch.addAction(Actions.fadeIn(0.5f));
		addActor(witch);
		
		startCutscene();
	}

	protected abstract void startCutscene();
	
	protected static String addWordRainbow(String text) {
		text += UI.color(Color.RED, " R");
		text += UI.color(Color.ORANGE, "A");
		text += UI.color(Color.YELLOW, "I");
		text += UI.color(Color.LIME, "N");
		text += UI.color(Color.ROYAL, "B");
		text += UI.color(Color.BLUE, "O");
		text += UI.color(Color.PURPLE, "W ");
		return text;
	}

	protected void makeSpeechBubble(String text, Runnable onClick) {
		Label bubble = UI.makeSpeechBubbleLabel(text);
		bubble.setPosition(Cutscene.SPEECH_BUBBLE_X, Cutscene.SPEECH_BUBBLE_Y);
		bubble.setColor(1, 1, 1, 0);
		bubble.addAction(Actions.delay(0.5f, Actions.fadeIn(0.5f)));
		bubble.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				onClick.run();
			}
		});
		currentBubble = bubble;
		addActor(currentBubble);
	}
}
