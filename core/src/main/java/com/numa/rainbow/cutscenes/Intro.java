package com.numa.rainbow.cutscenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.ui.UI;

public class Intro extends Group {

	private static final float SPEECH_BUBBLE_X = RainbowSeedGame.WORLD_WIDTH * 0.3f;
	private static final float SPEECH_BUBBLE_Y = RainbowSeedGame.WORLD_WIDTH * 0.3f;
	private Button nextButton;

	public Intro() {
		Actor clickBarrier = new Actor();
		clickBarrier.setSize(RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		addActor(clickBarrier);
		clickBarrier.debug();
		
		String text1 = UI.color(UI.DARK_BLUE, "So, it's already time for you to make\nyour first ");
		text1 += UI.color(Color.RED, "R");
		text1 += UI.color(Color.ORANGE, "A");
		text1 += UI.color(Color.GOLDENROD, "I");
		text1 += UI.color(Color.LIME, "N");
		text1 += UI.color(Color.ROYAL, "B");
		text1 += UI.color(Color.BLUE, "O");
		text1 += UI.color(Color.PURPLE, "W");
		text1 += UI.color(UI.DARK_BLUE, " seed? ");
		Label bubble1 = UI.makeSpeechBubbleLabel(text1);
		bubble1.setPosition(SPEECH_BUBBLE_X, SPEECH_BUBBLE_Y);
		addActor(bubble1);
		addAction(Actions.delay(3f, Actions.run(() -> {
			nextButton = UI.makeTextButton("Next ->", () -> {
				bubble1.addAction(Actions.fadeOut(0.5f));
				text2();
				nextButton.remove();
			});
			addActor(nextButton);
			nextButton.setPosition(SPEECH_BUBBLE_X * 2, SPEECH_BUBBLE_Y * 0.5f);
		})));
	}

	private void text2() {
		String text2 = UI.color(UI.DARK_BLUE, "My, how time flies.");
		Label bubble2 = UI.makeSpeechBubbleLabel(text2);
		bubble2.setPosition(SPEECH_BUBBLE_X, SPEECH_BUBBLE_Y);
		bubble2.setColor(1, 1, 1, 0);
		addActor(bubble2);
		bubble2.addAction(Actions.delay(0.5f, Actions.fadeIn(0.5f)));
		addAction(Actions.delay(3f, Actions.run(() -> {
			nextButton = UI.makeTextButton("Next ->", () -> {
				bubble2.addAction(Actions.fadeOut(0.5f));
				text3();
				nextButton.remove();
			});
			addActor(nextButton);
			nextButton.setPosition(SPEECH_BUBBLE_X * 2, SPEECH_BUBBLE_Y * 0.5f);
		})));
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
		Label bubble2 = UI.makeSpeechBubbleLabel(text);
		bubble2.setPosition(SPEECH_BUBBLE_X, SPEECH_BUBBLE_Y);
		bubble2.setColor(1, 1, 1, 0);
		addActor(bubble2);
		bubble2.addAction(Actions.delay(0.5f, Actions.fadeIn(0.5f)));
		addAction(Actions.delay(8f, Actions.run(() -> {
			nextButton = UI.makeTextButton("Next ->", () -> {
				bubble2.addAction(
						Actions.sequence(
						Actions.fadeOut(0.5f),
						Actions.removeActor()
								));
			});
			addActor(nextButton);
			nextButton.setPosition(SPEECH_BUBBLE_X * 2, SPEECH_BUBBLE_Y * 0.5f);
		})));
	}

}
