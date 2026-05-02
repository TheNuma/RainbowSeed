package com.numa.rainbow.ui;

import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.items.DraggableItem;
import com.numa.rainbow.items.ItemType;

public class UI {
	
	private static Skin skin;
	
	public static void initialize() {
		skin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));
		TooltipManager.getInstance().instant();
	}

	public static DraggableItem makeDraggableItem(ItemType type, Set<ItemType> combos) {
		DraggableItem item = new DraggableItem(type.getFileName(), type,combos);
		Label label = new Label(type.getItemName(), skin.get("title", LabelStyle.class));
		item.addListener(new Tooltip<Label>(label));
		return item;
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

	static Drawable getUISidebarTexture() {
		TextureRegion drawable = skin.getAtlas().findRegion("button");
		return new NinePatchDrawable(new NinePatch(drawable, 15, 15, 20, 20));
	}
	
	static Button getSpringButton(Runnable onClick) {
		ImageButtonStyle style = new ImageButtonStyle(skin.get("default", ButtonStyle.class));
		TextureRegionDrawable icon = new TextureRegionDrawable(new Texture(Gdx.files.internal("sprout.png")));
		float size = 0.6f * RainbowSeedGame.UI_WIDTH_FRACTION * RainbowSeedGame.WORLD_HEIGHT;
		icon.setMinSize(size, size);
		style.imageUp = icon;
		style.imageDown = icon.tint(Color.LIGHT_GRAY);
		ImageButton button = new ImageButton(style);
		button.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				onClick.run();
			}
		});
		return button;
	}

}
