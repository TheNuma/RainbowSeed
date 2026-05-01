package com.numa.rainbow.ui;

import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.numa.rainbow.items.ItemInteractions;
import com.numa.rainbow.items.ItemType;

public class UI {
	
	private static Skin skin;
	private static ItemInteractions interactions;
	
	public static void initialize() {
		skin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));
		interactions = new ItemInteractions();
	}
	
	public static DraggableItem makeDraggableItem(ItemType type) {
		return new DraggableItem(type.getFileName(), makeLabel(type.getItemName()),type,interactions.getCombinationsFor(type));
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
