package com.numa.rainbow.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.ui.UI;

public class SummerUnlocked extends Cutscene {

	public SummerUnlocked(Runnable endCutscene) {
		super(endCutscene);
	}

	@Override
	protected void startCutscene() {

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
			nextButton.setPosition(Cutscene.NEXT_BUTTON_X, Cutscene.NEXT_BUTTON_Y);
		};
		makeSpeechBubble(text1, next);
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
			nextButton.setPosition(Cutscene.NEXT_BUTTON_X, Cutscene.NEXT_BUTTON_Y);
		};
		makeSpeechBubble(text1, next);
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
			nextButton.setPosition(Cutscene.NEXT_BUTTON_X, Cutscene.NEXT_BUTTON_Y);
		};
		makeSpeechBubble(text, start);
		addAction(Actions.delay(6f, Actions.run(start)));
	}

}
