package com.numa.rainbow.ui;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.season.Seasonal;

public class BackgroundStage extends Stage implements Seasonal {

	private final Group spring;
	private final Group summer;
	private final Group autumn;
	private final Group winter;

	private Group currentBackground;

	public BackgroundStage(Viewport viewport) {
		super(viewport);

		spring = prepareSpringBackground();
		summer = prepareSummerBackground();
		autumn = prepareAutumnBackground();
		winter = prepareWinterBackground();

		currentBackground = summer; // This is intentional
	}

	public void changeBackground(Group newBackground) {
		float duration = 0.25f;
		addActor(newBackground);

		newBackground.setColor(1, 1, 1, 0);
		AlphaAction fadeOutOldBackground = new AlphaAction();
		fadeOutOldBackground.setAlpha(0f);
		fadeOutOldBackground.setDuration(duration);
		fadeOutOldBackground.setInterpolation(Interpolation.circleIn);
		currentBackground.addAction(fadeOutOldBackground);

		AlphaAction fadeInNewBackground = new AlphaAction();
		fadeInNewBackground.setAlpha(1f);
		fadeInNewBackground.setDuration(duration);
		fadeInNewBackground.setInterpolation(Interpolation.circleOut);
		newBackground.addAction(fadeInNewBackground);

		RunnableAction removeOldBackground = new RunnableAction();
		removeOldBackground.setRunnable(() -> {
			currentBackground.remove();
			currentBackground = newBackground;
		});
		DelayAction delayedRemoval = new DelayAction(duration);
		delayedRemoval.setAction(removeOldBackground);

		addAction(delayedRemoval);

	}

	@Override
	public void spring() {
		changeBackground(spring);
	}

	@Override
	public void summer() {
		changeBackground(summer);
	}

	@Override
	public void autumn() {
		changeBackground(autumn);
	}

	@Override
	public void winter() {
		changeBackground(winter);
	}

	@Override
	public void rainbow() {
		// TODO Auto-generated method stub
	}

	private Image makeDecorImage(String name, Color color, float scale) {
		return makeDecorImage(name, color, scale, false);
	}

	private Image makeDecorImage(String name, Color color, float scale, boolean randomRotation) {
		Image image = new Image(new Texture(Gdx.files.internal("decor/" + name + ".png")));
		image.setColor(color);
		image.setScale(scale * MathUtils.random(0.5f, 1f));
		image.setPosition(MathUtils.random(RainbowSeedGame.WORLD_WIDTH), MathUtils.random(RainbowSeedGame.WORLD_HEIGHT));
		if (MathUtils.randomBoolean()) {
			image.setScaleX(-1 * image.getScaleX());
		}
		if (randomRotation) {
			image.setRotation(MathUtils.random(360));
		}
		return image;
	}

	private Group prepareSpringBackground() {
		Group spring = new Group();
		Color backColor = new Color(Color.LIME).mul(new Color(Color.LIGHT_GRAY));
		spring.addActor(makeColoredBackground(backColor, (int) getWidth(), (int) getHeight()));

		Color decorColor = (new Color(Color.LIME)).mul(new Color(0.75f, 0.8f, 0.75f, 1));
		List<Color> pastels = List.of(Color.VIOLET, new Color(0.8f, 0.627451f, 0.8784314f, 1f), Color.SKY, Color.LIME, decorColor, decorColor, decorColor);
		for (int i = 0; i < 10; i++) {
			spring.addActor(makeDecorImage("curled-leaf", decorColor, 0.1f, true));
			spring.addActor(makeDecorImage("butterfly", decorColor, 0.15f));
			spring.addActor(makeDecorImage("butterfly-flower", decorColor, 0.15f));
			spring.addActor(makeDecorImage("caterpillar", decorColor, 0.15f));
		}
		for (int i = 0; i < 7; i++) {
			spring.addActor(makeDecorImage("sprout", decorColor, 0.1f));
		}
		for (int i = 0; i < 11; i++) {
			spring.addActor(makeDecorImage("new-shoot", decorColor, 0.1f));
		}
		for (int i = 0; i < 20; i++) {
			spring.addActor(makeDecorImage("dot1", pastels.get(MathUtils.random(pastels.size() - 1)), 0.6f));
			spring.addActor(makeDecorImage("dot2", pastels.get(MathUtils.random(pastels.size() - 1)), 0.6f));
			spring.addActor(makeDecorImage("dot3", pastels.get(MathUtils.random(pastels.size() - 1)), 0.6f));
		}
		for (int i = 0; i < 20; i++) {
			spring.addActor(makeDecorImage("wave1", decorColor, 0.3f));
			spring.addActor(makeDecorImage("wave2", decorColor, 0.3f));
			spring.addActor(makeDecorImage("wave3", decorColor, 0.3f));
		}

		return spring;
	}

	private Group prepareSummerBackground() {
		Group summer = new Group();
		Color backColor = new Color(Color.LIME).mul(new Color(Color.GRAY));
		summer.addActor(makeColoredBackground(backColor, (int) getWidth(), (int) getHeight()));

		Color decorColor = new Color(Color.LIME).mul(new Color(Color.LIGHT_GRAY)).mul(new Color(0.75f, 0.75f, 0.75f, 1));
		for (int i = 0; i < 10; i++) {
			summer.addActor(makeDecorImage("agave", decorColor, 0.1f));
		}
		for (int i = 0; i < 7; i++) {
			summer.addActor(makeDecorImage("grass", decorColor, 0.1f));
			summer.addActor(makeDecorImage("cigale", decorColor, 0.12f, true));
			summer.addActor(makeDecorImage("cricket", decorColor, 0.14f));
			summer.addActor(makeDecorImage("dragonfly", decorColor, 0.13f));
		}
		for (int i = 0; i < 11; i++) {
			summer.addActor(makeDecorImage("papyrus", decorColor, 0.2f));
		}
		for (int i = 0; i < 50; i++) {
			summer.addActor(makeDecorImage("dot1", decorColor, 0.6f));
			summer.addActor(makeDecorImage("dot2", decorColor, 0.6f));
			summer.addActor(makeDecorImage("dot3", decorColor, 0.6f));
		}
		return summer;
	}

	private Group prepareAutumnBackground() {
		Group autumn = new Group();
		Color backColor = new Color(Color.ORANGE).mul(new Color(Color.GRAY));
		autumn.addActor(makeColoredBackground(backColor, (int) getWidth(), (int) getHeight()));

		Color decorColor = new Color(Color.ORANGE).mul(new Color(Color.LIGHT_GRAY).mul(new Color(Color.LIGHT_GRAY)));
		for (int i = 0; i < 10; i++) {
			autumn.addActor(makeDecorImage("chestnut-leaf", decorColor, 0.1f, true));
		}
		for (int i = 0; i < 7; i++) {
			autumn.addActor(makeDecorImage("falling-leaf", decorColor, 0.1f, true));
		}
		for (int i = 0; i < 11; i++) {
			autumn.addActor(makeDecorImage("ginkgo-leaf", decorColor, 0.1f, true));
		}
		for (int i = 0; i < 11; i++) {
			autumn.addActor(makeDecorImage("oak-leaf", decorColor, 0.1f, true));
		}
		for (int i = 0; i < 70; i++) {
			autumn.addActor(makeDecorImage("dot1", decorColor, 0.6f));
			autumn.addActor(makeDecorImage("dot2", decorColor, 0.6f));
			autumn.addActor(makeDecorImage("dot3", decorColor, 0.6f));
		}
		return autumn;
	}

	private Group prepareWinterBackground() {
		Group winter = new Group();
		Color backColor = new Color(Color.SKY).mul(new Color(Color.LIGHT_GRAY));
		winter.addActor(makeColoredBackground(backColor, (int) getWidth(), (int) getHeight()));

		Color decorColor = new Color(Color.SKY).mul(new Color(0.8f, 0.8f, 0.8f, 1f));
		for (int i = 0; i < 10; i++) {
			winter.addActor(makeDecorImage("cold-heart", decorColor, 0.1f, true));
		}
		for (int i = 0; i < 7; i++) {
			winter.addActor(makeDecorImage("snowflake-1", decorColor, 0.1f, true));
		}
		for (int i = 0; i < 11; i++) {
			winter.addActor(makeDecorImage("snowflake-2", decorColor, 0.1f, true));
		}
		for (int i = 0; i < 15; i++) {
			winter.addActor(makeDecorImage("dot1", decorColor, 0.6f));
			winter.addActor(makeDecorImage("dot2", decorColor, 0.6f));
			winter.addActor(makeDecorImage("dot3", decorColor, 0.6f));
		}
		for (int i = 0; i < 20; i++) {
			winter.addActor(makeDecorImage("swirl1", decorColor, 0.6f));
			winter.addActor(makeDecorImage("wave1", decorColor, 0.6f));
			winter.addActor(makeDecorImage("wave2", decorColor, 0.6f));
			winter.addActor(makeDecorImage("wave3", decorColor, 0.6f));
		}
		return winter;
	}

	public static Image makeColoredBackground(Color color, int width, int height) {
		Pixmap coloredMap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		coloredMap.setColor(color);
		coloredMap.fillRectangle(0, 0, coloredMap.getWidth(), coloredMap.getHeight());
		Texture coloredTexture = new Texture(coloredMap);
		coloredMap.dispose();
		return new Image(coloredTexture);
	}

}