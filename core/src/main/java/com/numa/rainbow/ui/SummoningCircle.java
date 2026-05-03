package com.numa.rainbow.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.items.DraggableItem;
import com.numa.rainbow.season.Seasonal;

public class SummoningCircle extends Group implements Seasonal {

	private Runnable completeTheRitual;
	private float circleRadius;

	private List<DraggableItem> receivedItems;
	private Image circle;
	private Image circleHint;

	public SummoningCircle(Runnable completeTheRitual) {
		this.completeTheRitual = completeTheRitual;
		Texture tex = new Texture(Gdx.files.internal("ui/summoningCircle.png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		circle = new Image(tex);
		circle.setPosition(RainbowSeedGame.WORLD_WIDTH / 2f, RainbowSeedGame.WORLD_HEIGHT / 2f, Align.center);
		addActor(circle);
		
		circleHint = new Image(tex);
		circleHint.setSize(circleHint.getWidth() * 1.03f, circleHint.getHeight() * 1.03f);
		circleHint.setColor(1, 1, 1, 0f);
		circleHint.setPosition(RainbowSeedGame.WORLD_WIDTH / 2f, RainbowSeedGame.WORLD_HEIGHT / 2f, Align.center);
		addActor(circleHint);
		
		receivedItems = new ArrayList<>();
		circleRadius = tex.getHeight()/2f;
	}

	@Override
	public void spring() {
		setVisible(true);

		circle.clearActions();
		circle.setColor(1, 1, 1, 0f);
		circle.addAction(Actions.fadeIn(1f));
	}

	@Override
	public void summer() {
		setVisible(false);
	}

	@Override
	public void autumn() {
		setVisible(false);
	}

	@Override
	public void winter() {
		setVisible(false);
	}

	@Override
	public void rainbow() {
		setVisible(false);
	}

	public void acceptItem(DraggableItem draggedItem) {
		draggedItem.setVisible(false);

		receivedItems.add(draggedItem);
		int jauntyOffset = 12;
		float angle = 360f / 7;
		angle *= receivedItems.size();
		angle += jauntyOffset;
		float x = circleRadius * MathUtils.cosDeg(angle);
		float y = circleRadius * MathUtils.sinDeg(angle);

		draggedItem.setPosition(RainbowSeedGame.WORLD_WIDTH / 2f + x, RainbowSeedGame.WORLD_HEIGHT / 2f + y, Align.center);
		if (receivedItems.size() == 7) {
			addAction(Actions.delay(0.75f, Actions.run(completeTheRitual)));
			receivedItems.forEach(item -> item.addAction(
					Actions.parallel(
							Actions.moveTo(
									RainbowSeedGame.WORLD_WIDTH / 2f - draggedItem.getWidth()/2f, 
									RainbowSeedGame.WORLD_HEIGHT / 2f - draggedItem.getHeight()/2f,
									0.75f,
									Interpolation.circleIn)
							,
							Actions.delay(0.45f, Actions.fadeOut(0.3f))
							)
					));
		}
	}
	
	public void hintDroppable() {
		if (circle.isVisible() && circleHint.getColor().a < 0.1f && !circleHint.hasActions()) {
			circleHint.clearActions();
			circleHint.setColor(1, 1, 1, 0f);
			circleHint.addAction(Actions.color(new Color(1,1,1,0.2f), 0.1f));
		}
	}
	
	public void unhintDroppable() {
		if (circle.isVisible()) {
			circleHint.clearActions();
			circleHint.addAction(Actions.fadeOut(0.1f));
		}
	}

}
