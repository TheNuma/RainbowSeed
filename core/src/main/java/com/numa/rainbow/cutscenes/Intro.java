package com.numa.rainbow.cutscenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.numa.rainbow.ui.UI;

public class Intro extends Cutscene {

	public Intro(Runnable endCutscene) {
		super(endCutscene);
	}

	@Override
	protected void startCutscene() {
		darkScreen.clearActions();
		darkScreen.setColor(Color.WHITE);
		String text1 = UI.color(UI.DARK_BLUE, "So, Spring has come and you're already old\nenough to craft your first");
		text1 = addWordRainbow(text1);
		text1 += UI.color(UI.DARK_BLUE, "seed?");
		Runnable next = makeNextButtonRunnable(this::text2);
		makeSpeechBubble(text1, next);
		addAction(Actions.delay(3f, Actions.run(next)));
	}

	private void text2() {
		String text2 = UI.color(UI.DARK_BLUE, "My, how time flies.");
		Runnable next = makeNextButtonRunnable(this::text3);
		makeSpeechBubble(text2, next);
		addAction(Actions.delay(3f, Actions.run(next)));
	}
	
	private void text3() {
		String text = UI.color(UI.DARK_BLUE, "You'll need to combine gardening items\nto create new ones. Find the 7 colorful plants,\nand merge them to create the");
		text = addWordRainbow(text);
		text += UI.color(UI.DARK_BLUE, "seed!");

		Runnable start = makeFinalButtonRunnable("START!!", () -> {});
		makeSpeechBubble(text, start);
		addAction(Actions.delay(6f, Actions.run(start)));
	}

}
