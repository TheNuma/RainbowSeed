package com.numa.rainbow.ui;

import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.items.DraggableItem;
import com.numa.rainbow.items.ItemType;
import com.numa.rainbow.season.Season;
import com.numa.rainbow.season.SeasonShifter;

public class UI {
	
	private static final String SPEECH_BUBBLE_LABEL = "speechbubble";
	private static final String LABEL_WITH_BACKGROUND = "labelWithBackground";
	
	public static final Color DARK_BLUE = new Color(0.1f, 0.2f, 0.5f, 1);
	
	private static Skin skin;
	
	public static void initialize() {
		skin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));

		LabelStyle style = new LabelStyle(skin.get("button", LabelStyle.class));
		style.font.getData().markupEnabled = true;
		style.background = getBasicBackgroundTexture(Color.GOLDENROD);
		skin.add(LABEL_WITH_BACKGROUND, style);
		
		Texture speechBubbleTexture = new Texture(Gdx.files.internal("ui/speechbubble.png"));
		speechBubbleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		int width = speechBubbleTexture.getWidth();
		int height = speechBubbleTexture.getHeight();
		NinePatchDrawable speechBubbleBackground = new NinePatchDrawable(new NinePatch(speechBubbleTexture, (int) (0.45f * width), (int) (0.45f * width), (int) (0.3f * height), (int) (0.5f * height)));
		speechBubbleBackground.setPadding((int) (0.1f * height), (int) (0.1f * width), (int) (0.425f * height), (int) (0.1f * width));
		speechBubbleBackground.setMinSize(width, height);
		LabelStyle speechbubbleStyle = new LabelStyle(skin.get("title", LabelStyle.class));
		speechbubbleStyle.font.getData().markupEnabled = true;
		speechbubbleStyle.font.getData().setLineHeight(speechbubbleStyle.font.getData().lineHeight * 1.5f);
		speechbubbleStyle.background = speechBubbleBackground;
		skin.add(SPEECH_BUBBLE_LABEL, speechbubbleStyle);
		
		TooltipManager.getInstance().instant();
	}

	public static DraggableItem makeDraggableItem(ItemType type, Set<ItemType> combos, SeasonShifter seasonShifter) {
		DraggableItem item = new DraggableItem(type.getFileName(), type,combos);
		SeasonalLabel label = new SeasonalLabel(type.getItemName(), skin.get("title", LabelStyle.class));
		seasonShifter.registerSeasonalThing(label);
		item.addListener(new Tooltip<Label>(label));
		return item;
	}

	public static Label makeLabel(String text) {
		return new Label(text, skin);
	}

	public static Label makeTitleLabel(String text) {
		return new Label(text, skin.get("title", LabelStyle.class));
	}

	public static Label makeLabelWithBackground(String text) {
		return new Label(color(DARK_BLUE, text), skin.get(LABEL_WITH_BACKGROUND, LabelStyle.class));
	}
	
	public static Label makeSpeechBubbleLabel(String text) {
		return new Label(text, skin.get(SPEECH_BUBBLE_LABEL, LabelStyle.class));
	}
	
	public static TextButton makeTextButton(String text, Runnable onClick) {
		TextButton button = new TextButton(color(Color.WHITE, text), skin);
		button.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				onClick.run();
			}
		});
		return button;
	}

	static Drawable getUISidebarTexture() {
		return getBasicBackgroundTexture(Color.TEAL);
	}
	static Drawable getBasicBackgroundTexture(Color color) {
		TextureRegion drawable = skin.getAtlas().findRegion("button");
		NinePatchDrawable ninepatch = new NinePatchDrawable(new NinePatch(drawable, 15, 15, 20, 20));
		return ninepatch.tint(color);
	}
	
	static Button getSeasonButton(Season season, Runnable onClick) {
		ImageButton button = new ImageButton(getSeasonButtonStyle(season));
		button.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				onClick.run();
			}
		});
		return button;
	}
	
	private static ImageButtonStyle getSeasonButtonStyle(Season season) {
		ImageButtonStyle style = new ImageButtonStyle(skin.get("default", ButtonStyle.class));
		
		String iconFileName = "";
		switch (season) {
		case AUTUMN:
			iconFileName = "oak-leaf";
			break;
		case SPRING:
			iconFileName = "sprout";
			break;
		case SUMMER:
			iconFileName = "sun";
			break;
		case WINTER:
			iconFileName = "snowflake-2";
			break;
		};

		TextureRegionDrawable icon = new TextureRegionDrawable(new Texture(Gdx.files.internal("ui/" + iconFileName + ".png")));
		float size = 0.6f * RainbowSeedGame.UI_WIDTH_FRACTION * RainbowSeedGame.WORLD_HEIGHT;
		icon.setMinSize(size, size);
		style.imageUp = icon;
		style.imageDown = icon.tint(Color.LIGHT_GRAY);
		return style;
	}
	
	public static String color(Color color, String text) {
		return "[#" + color + "]" + text;
	}

}
