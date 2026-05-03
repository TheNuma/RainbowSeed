package com.numa.rainbow.ui;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.numa.rainbow.RainbowSeedGame;

public class RainbowSeed extends Group {
	
	private static final List<Color> SHINE_COLORS = List.of(Color.RED, Color.ORANGE, Color.YELLOW, Color.LIME, Color.ROYAL,Color.BLUE,Color.PURPLE);
	
	private static final float SHINE_DELAY = 0.03f;
	private float currentDelay;

	private Image seed;

	public RainbowSeed() {
		Image darkScreen = BackgroundStage.makeColoredBackground(new Color(0,0,0,0.5f), RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		darkScreen.setSize(RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		darkScreen.setColor(0, 0, 0, 0);
		darkScreen.addAction(Actions.fadeIn(0.5f));
		addActor(darkScreen);
		
		Texture tex = new Texture(Gdx.files.internal("items/rainbow-seed.png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		seed = new Image(tex);
		seed.setPosition(RainbowSeedGame.WORLD_WIDTH / 2f, RainbowSeedGame.WORLD_HEIGHT / 2f, Align.center);
		seed.setColor(1, 1, 1, 0f);
		seed.addAction(Actions.fadeIn(1f));
		addActor(seed);
		
		Label theEnd = UI.makeTitleLabel("The End!");
		addActor(theEnd);
		theEnd.setColor(1,1,1,0);
		theEnd.setPosition(RainbowSeedGame.WORLD_WIDTH /2f - theEnd.getWidth()/2f, RainbowSeedGame.WORLD_HEIGHT*0.1f);
		theEnd.addAction(Actions.delay(2f, Actions.fadeIn(1f)));
		
		currentDelay = 0;
	}
	
	@Override
	public void act(float delta) {
		currentDelay -= delta;
		if (currentDelay <= 0) {
			currentDelay = SHINE_DELAY;

			Texture tex = new Texture(Gdx.files.internal("ui/shine.png"));
			tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			Image shine = new Image(tex);
			Color color = SHINE_COLORS.get(MathUtils.random(SHINE_COLORS.size()-1));
			shine.setOrigin(Align.left);
			shine.setPosition(RainbowSeedGame.WORLD_WIDTH / 2f, RainbowSeedGame.WORLD_HEIGHT/2f-30);
			shine.rotateBy(MathUtils.random(360));
			addActor(shine);
			shine.setColor(color.r, color.g, color.b, 0);
			shine.addAction(Actions.sequence(
					Actions.fadeIn(0.2f),
					Actions.delay(0.4f),
					Actions.fadeOut(0.2f),
					Actions.removeActor()
					));
			shine.setScale(MathUtils.random(0.5f, 1.5f), MathUtils.random(0.9f, 1.1f));
		}
		seed.toFront();
		super.act(delta);
	}
}
