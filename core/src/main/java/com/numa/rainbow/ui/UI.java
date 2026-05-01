package com.numa.rainbow.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class UI {
	
	private static Skin skin;
	
	public static void initialize() {
		skin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));
	}
	public static Label makeLabel (String text) {
		return new Label(text, skin);
	}
	
	public static TextButton makeTextButton(String text, Runnable onClick) {
		TextButton button = new TextButton(text, skin);
		button.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				onClick.run();
			}
		});
		return button;
	}

}
