package com.numa.rainbow.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.items.DraggableItem;
import com.numa.rainbow.season.Seasonal;

public class SummoningCircle extends Image implements Seasonal {

	private Runnable completeTheRitual;
	private float circleRadius;

	private List<DraggableItem> receivedItems;

	public SummoningCircle(Runnable completeTheRitual) {
		this.completeTheRitual = completeTheRitual;
		Texture tex = new Texture(Gdx.files.internal("ui/summoningCircle.png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		setDrawable(new TextureRegionDrawable(tex));
		setSize(tex.getWidth(), tex.getHeight());
		setPosition(RainbowSeedGame.WORLD_WIDTH / 2f, RainbowSeedGame.WORLD_HEIGHT / 2f, Align.center);
		receivedItems = new ArrayList<>();
		circleRadius = tex.getHeight()/2f;
	}

	@Override
	public void spring() {
		setVisible(true);

		clearActions();
		setColor(1, 1, 1, 0f);
		addAction(Actions.fadeIn(1f));
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

}
