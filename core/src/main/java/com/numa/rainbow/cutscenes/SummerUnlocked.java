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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.ui.BackgroundStage;
import com.numa.rainbow.ui.UI;

public class SummerUnlocked extends Group {

	private static final float SPEECH_BUBBLE_X = RainbowSeedGame.WORLD_WIDTH * 0.3f;
	private static final float SPEECH_BUBBLE_Y = RainbowSeedGame.WORLD_WIDTH * 0.35f;
	private static final float NEXT_BUTTON_X = SPEECH_BUBBLE_X * 1.8f;
	private static final float NEXT_BUTTON_Y = SPEECH_BUBBLE_Y * 0.95f;

	private Button nextButton;
	private Actor currentBubble;
	private Runnable endCutscene;
	
	private Image witch;
	private Actor darkScreen;

	public SummerUnlocked(Runnable endCutscene) {
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
		
		
		
		String text1 = UI.color(UI.DARK_BLUE, "You're doing well! I'd say it's time\nfor me to teach you something fun...");
		Runnable next = () -> {
			clearActions();
			currentBubble.clearListeners();
			nextButton = UI.makeTextButton("Next ->", () -> {
				currentBubble.addAction(Actions.fadeOut(0.5f));
				text2();
				nextButton.remove();
			});
			addActor(nextButton);
			nextButton.setPosition(NEXT_BUTTON_X, NEXT_BUTTON_Y);
		};
		Label bubble1 = makeSpeechBubble(text1, next);
		currentBubble = bubble1;
		addActor(currentBubble);
		addAction(Actions.delay(3f, Actions.run(next)));
	}

	private void text2() {
		String text1 = UI.color(UI.DARK_BLUE, "You may have guessed that the ingredients\nfor the ");
		text1 += UI.color(Color.RED, "R");
		text1 += UI.color(Color.ORANGE, "A");
		text1 += UI.color(Color.YELLOW, "I");
		text1 += UI.color(Color.LIME, "N");
		text1 += UI.color(Color.ROYAL, "B");
		text1 += UI.color(Color.BLUE, "O");
		text1 += UI.color(Color.PURPLE, "W");
		text1 += UI.color(UI.DARK_BLUE, " seed cannot be found in\nSpring alone.");
		Runnable next = () -> {
			clearActions();
			currentBubble.clearListeners();
			nextButton = UI.makeTextButton("Next ->", () -> {
				currentBubble.addAction(Actions.fadeOut(0.5f));
				text3();
				nextButton.remove();
			});
			addActor(nextButton);
			nextButton.setPosition(NEXT_BUTTON_X, NEXT_BUTTON_Y);
		};
		Label bubble2 = makeSpeechBubble(text1, next);
		currentBubble = bubble2;
		addActor(bubble2);
		addAction(Actions.delay(3f, Actions.run(next)));
	}
	
	private void text3() {
		String text = UI.color(UI.DARK_BLUE, "With this spell, you can shift your\nseasonal reality and access new plants.\nEnjoy the heat of summer!");
		Texture texGesture = new Texture(Gdx.files.internal("ui/witchGesturing.png"));
		texGesture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		witch.setDrawable(new TextureRegionDrawable(texGesture));
		Texture spellTexture = new Texture(Gdx.files.internal("ui/sun.png"));
		spellTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image spell = new Image(spellTexture);
		spell.setSize(150, 150);
		spell.setColor(1, 1, 1, 0.4f);
		addActor(spell);

		Image spell2 = new Image(spellTexture);
		spell2.setBounds(RainbowSeedGame.WORLD_WIDTH*0.25f, RainbowSeedGame.WORLD_HEIGHT * 0.37f, 100, 100);
		addActor(spell2);
		spell.setPosition(spell2.getX() + spell2.getWidth()/2f, spell2.getY() + spell2.getHeight()/2f, Align.center);

		Runnable start = () -> {
			currentBubble.clearListeners();
			clearActions();
			nextButton = UI.makeTextButton("Let's go!", () -> {
				clearActions();
				nextButton.remove();
				witch.addAction(Actions.fadeOut(0.5f));
				darkScreen.addAction(Actions.fadeOut(0.5f));
				spell.addAction(Actions.fadeOut(0.5f));
				spell2.addAction(Actions.fadeOut(0.5f));
				currentBubble.addAction(
						Actions.sequence(
						Actions.fadeOut(0.5f),
						Actions.run(endCutscene)
								));
			});
			addActor(nextButton);
			nextButton.setPosition(NEXT_BUTTON_X, NEXT_BUTTON_Y);
		};
		Label bubble3 = makeSpeechBubble(text, start);
		currentBubble = bubble3;
		addActor(bubble3);
		addAction(Actions.delay(6f, Actions.run(start)));
	}
	
	private static Label makeSpeechBubble(String text, Runnable onClick) {
		Label bubble = UI.makeSpeechBubbleLabel(text);
		bubble.setPosition(SPEECH_BUBBLE_X, SPEECH_BUBBLE_Y);
		bubble.setColor(1, 1, 1, 0);
		bubble.addAction(Actions.delay(0.5f, Actions.fadeIn(0.5f)));
		bubble.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				onClick.run();
			}
		});
		return bubble;
	}

}
