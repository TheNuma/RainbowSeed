package com.numa.rainbow.cutscenes;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.numa.rainbow.ui.UI;

public class Intro extends Cutscene {

	public Intro(Runnable endCutscene) {
		super(endCutscene);
	}
	
	@Override
	protected void startCutscene() {
		String text1 = UI.color(UI.DARK_BLUE, "So, Spring has come and you're already old\nenough to craft your first");
		text1 = addWordRainbow(text1);
		text1 += UI.color(UI.DARK_BLUE, "seed? ");
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
		makeSpeechBubble(text1, next);
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
			nextButton.setPosition(NEXT_BUTTON_X, NEXT_BUTTON_Y);
		};
		makeSpeechBubble(text2, next);
		addAction(Actions.delay(3f, Actions.run(next)));
	}
	
	private void text3() {
		String text = UI.color(UI.DARK_BLUE, "You'll need to combine gardening items\nto create new ones. Find the 7 colorful plants,\nand merge them to create the");
		text = addWordRainbow(text);
		text += UI.color(UI.DARK_BLUE, "seed!");
		Runnable start = () -> {
			currentBubble.clearListeners();
			clearActions();
			nextButton = UI.makeTextButton("Start!", () -> {
				clearActions();
				nextButton.remove();
				witch.addAction(Actions.fadeOut(0.5f));
				darkScreen.addAction(Actions.fadeOut(0.5f));
				currentBubble.addAction(
						Actions.sequence(
						Actions.fadeOut(0.5f),
						Actions.run(endCutscene)
								));
			});
			addActor(nextButton);
			nextButton.setPosition(NEXT_BUTTON_X, NEXT_BUTTON_Y);
		};
		makeSpeechBubble(text, start);
		addAction(Actions.delay(6f, Actions.run(start)));
	}

}
