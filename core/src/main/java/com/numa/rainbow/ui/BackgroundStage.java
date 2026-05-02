package com.numa.rainbow.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.numa.rainbow.season.Seasonal;

public class BackgroundStage extends Stage implements Seasonal {

	private final Group spring;
	private final Group summer;
	private final Group autumn;
	private final Group winter;

	public BackgroundStage(Viewport viewport) {
		super(viewport);

		spring = prepareSpringBackground();
		summer = prepareSummerBackground();
		autumn = prepareAutumnBackground();
		winter = prepareWinterBackground();
	}

	@Override
	public void spring() {
		clear();
		addActor(spring);
	}

	@Override
	public void summer() {
		clear();
		addActor(summer);
	}

	@Override
	public void autumn() {
		clear();
		addActor(autumn);
	}

	@Override
	public void winter() {
		clear();
		addActor(winter);
	}
	
	private Group prepareSpringBackground() {
		Group spring = new Group();
		spring.addActor(makeColoredBackground(new Color(Color.LIME).mul(new Color( Color.LIGHT_GRAY))));
		return spring;
	}
	
	private Group prepareSummerBackground() {
		Group summer = new Group();
		summer.addActor(makeColoredBackground(new Color(Color.LIME).mul(new Color(Color.GRAY))));
		return summer;
	}

	private Group prepareAutumnBackground() {
		Group autumn = new Group();
		autumn.addActor(makeColoredBackground(new Color(Color.ORANGE).mul(new Color(Color.GRAY))));
		return autumn;
	}
	
	private Group prepareWinterBackground() {
		Group winter = new Group();
		winter.addActor(makeColoredBackground(new Color(Color.SKY)));
		return winter;
	}

	private Image makeColoredBackground(Color color) {
		Pixmap coloredMap = new Pixmap((int)getWidth(), (int)getHeight(), Pixmap.Format.RGBA8888);
		coloredMap.setColor(color);
		coloredMap.fillRectangle(0, 0, coloredMap.getWidth(), coloredMap.getHeight());
		Texture coloredTexture = new Texture(coloredMap);
		coloredMap.dispose();
		return new Image(coloredTexture);
	}

}
