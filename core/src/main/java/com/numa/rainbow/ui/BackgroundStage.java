package com.numa.rainbow.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BackgroundStage extends Stage {

	private final Group spring;
	private final Group summer;
	private final Group autumn;
	private final Group winter;

	public BackgroundStage(Viewport viewport) {
		super(viewport);

		spring = new Group();
		summer = new Group();
		autumn = new Group();
		winter = new Group();
		
		prepareSpringBackground();
		
		setToSpring();
	}

	private void setToSpring() {
		clear();
		addActor(spring);
	}
	
	private void prepareSpringBackground() {
		Pixmap coloredMap = new Pixmap((int)getWidth(), (int)getHeight(), Pixmap.Format.RGBA8888);
		coloredMap.setColor(Color.LIME.mul(Color.DARK_GRAY));
		coloredMap.fillRectangle(0, 0, coloredMap.getWidth(), coloredMap.getHeight());
		Texture coloredTexture = new Texture(coloredMap);
		coloredMap.dispose();
		Image springGreenBack = new Image(coloredTexture);
		spring.addActor(springGreenBack);
	}
	

}
