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

public class WinterUnlocked extends Cutscene {

	public WinterUnlocked(Runnable endCutscene) {
		super(endCutscene);
	}
	
	@Override
	protected void startCutscene() {
		String text1 = UI.color(UI.DARK_BLUE, "Summer is lovely, but the");
		text1 = addWordRainbow(text1);
		text1 += UI.color(UI.DARK_BLUE, "seed \nrequires balance and variety.");
		Runnable next = makeNextButtonRunnable(this::text2);
		makeSpeechBubble(text1, next);
		addAction(Actions.delay(3f, Actions.run(next)));
	}

	private void text2() {
		String text = UI.color(UI.DARK_BLUE, "The potent heat of Summer supports the\nvitality of a plant, but so too does the\nrestful cold of Winter.");
		Texture texGesture = new Texture(Gdx.files.internal("ui/witchGesturing.png"));
		texGesture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		witch.setDrawable(new TextureRegionDrawable(texGesture));
		Texture spellTexture = new Texture(Gdx.files.internal("ui/snowflake-2.png"));
		spellTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image spell = new Image(spellTexture);
		spell.setSize(150, 150);
		spell.setColor(1, 1, 1, 0f);
		spell.addAction(Actions.color(new Color(1, 1, 1, 0.4f), 0.5f));
		addActor(spell);

		Image spell2 = new Image(spellTexture);
		spell2.setBounds(RainbowSeedGame.WORLD_WIDTH*0.25f, RainbowSeedGame.WORLD_HEIGHT * 0.37f, 100, 100);
		spell2.setColor(1, 1, 1, 0f);
		spell2.addAction(Actions.fadeIn(0.5f));
		addActor(spell2);
		spell.setPosition(spell2.getX() + spell2.getWidth()/2f, spell2.getY() + spell2.getHeight()/2f, Align.center);


		Runnable start = makeFinalButtonRunnable("Let it snow!", () -> {
			spell.addAction(Actions.fadeOut(0.5f));
			spell2.addAction(Actions.fadeOut(0.5f));
			});
		makeSpeechBubble(text, start);
		addAction(Actions.delay(4f, Actions.run(start)));
	}

}
