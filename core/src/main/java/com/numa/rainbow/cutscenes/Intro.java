package com.numa.rainbow.cutscenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.ui.UI;

public class Intro extends Group {

	private static final float SPEECH_BUBBLE_X = RainbowSeedGame.WORLD_WIDTH * 0.3f;
	private static final float SPEECH_BUBBLE_Y = RainbowSeedGame.WORLD_WIDTH * 0.3f;

	private Button nextButton;
	private Actor currentBubble;
	private Runnable endCutscene;

	public Intro(Runnable endCutscene) {
		this.endCutscene = () -> {
			endCutscene.run();
			remove();
		};
		Actor clickBarrier = new Actor();
		clickBarrier.setSize(RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		addActor(clickBarrier);
		
		String text1 = UI.color(UI.DARK_BLUE, "So, it's already time for you to make\nyour first ");
		text1 += UI.color(Color.RED, "R");
		text1 += UI.color(Color.ORANGE, "A");
		text1 += UI.color(Color.GOLDENROD, "I");
		text1 += UI.color(Color.LIME, "N");
		text1 += UI.color(Color.ROYAL, "B");
		text1 += UI.color(Color.BLUE, "O");
		text1 += UI.color(Color.PURPLE, "W");
		text1 += UI.color(UI.DARK_BLUE, " seed? ");
		Runnable next = () -> {
			clearActions();
			currentBubble.clearListeners();
			nextButton = UI.makeTextButton("Next ->", () -> {
				currentBubble.addAction(Actions.fadeOut(0.5f));
				text2();
				nextButton.remove();
			});
			addActor(nextButton);
			nextButton.setPosition(SPEECH_BUBBLE_X * 2, SPEECH_BUBBLE_Y * 0.5f);
		};
		Label bubble1 = makeSpeechBubble(text1, next);
		currentBubble = bubble1;
		addActor(currentBubble);
		addAction(Actions.delay(3f, Actions.run(next)));
	}

	private void text2() {
		String text2 = UI.color(UI.DARK_BLUE, "My, how time flies.");
		Runnable next = () -> {
			clearActions();
			currentBubble.clearListeners();
			nextButton = UI.makeTextButton("Next ->", () -> {
				currentBubble.addAction(Actions.fadeOut(0.5f));
				text3();
				nextButton.remove();
			});
			addActor(nextButton);
			nextButton.setPosition(SPEECH_BUBBLE_X * 2, SPEECH_BUBBLE_Y * 0.5f);
		};
		Label bubble2 = makeSpeechBubble(text2, next);
		currentBubble = bubble2;
		addActor(bubble2);
		addAction(Actions.delay(3f, Actions.run(next)));
	}
	
	private void text3() {
		String text = UI.color(UI.DARK_BLUE, "You'll need to combine gardening items\nto create new ones. Find the 7 colorful plants,\nand merge them to create the ");
		text += UI.color(Color.RED, "R");
		text += UI.color(Color.ORANGE, "A");
		text += UI.color(Color.GOLDENROD, "I");
		text += UI.color(Color.LIME, "N");
		text += UI.color(Color.ROYAL, "B");
		text += UI.color(Color.BLUE, "O");
		text += UI.color(Color.PURPLE, "W");
		text += UI.color(UI.DARK_BLUE, " seed!");
		Runnable start = () -> {
			currentBubble.clearListeners();
			clearActions();
			nextButton = UI.makeTextButton("Start!", () -> {
				clearActions();
				nextButton.remove();
				currentBubble.addAction(
						Actions.sequence(
						Actions.fadeOut(0.5f),
						Actions.run(endCutscene)
								));
			});
			addActor(nextButton);
			nextButton.setPosition(SPEECH_BUBBLE_X * 2, SPEECH_BUBBLE_Y * 0.5f);
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
