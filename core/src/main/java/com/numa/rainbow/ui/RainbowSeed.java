package com.numa.rainbow.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.numa.rainbow.RainbowSeedGame;

public class RainbowSeed extends Group {

	public RainbowSeed() {
		Image darkScreen = BackgroundStage.makeColoredBackground(new Color(0,0,0,0.5f), RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		darkScreen.setSize(RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		darkScreen.setColor(0, 0, 0, 0);
		darkScreen.addAction(Actions.fadeIn(0.5f));
		addActor(darkScreen);
		
		Texture tex = new Texture(Gdx.files.internal("items/rainbow-seed.png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image seed = new Image(tex);
		seed.setPosition(RainbowSeedGame.WORLD_WIDTH / 2f, RainbowSeedGame.WORLD_HEIGHT / 2f, Align.center);
		seed.setColor(1, 1, 1, 0f);
		seed.addAction(Actions.fadeIn(1f));
		addActor(seed);
	}

}
