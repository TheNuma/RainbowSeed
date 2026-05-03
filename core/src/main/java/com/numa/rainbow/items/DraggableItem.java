package com.numa.rainbow.items;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.season.Season;
import com.numa.rainbow.season.Seasonal;
import com.numa.rainbow.ui.PossibleComboHint;
import com.numa.rainbow.ui.SummoningCircle;
import com.numa.rainbow.ui.UI;

public class DraggableItem extends Image implements Seasonal {

	private final DragAndDrop dragAndDrop;
	private Map<ItemType, Target> dragAndDropTargets;

	private String name;
	private Set<ItemType> remainingCombinations;

	private ItemType type;

	public DraggableItem(String fileName, ItemType type, Set<ItemType> combos) {
		this.type=type;
		this.remainingCombinations=combos;
		this.name = type.getItemName();

		Texture tex = new Texture(Gdx.files.internal("items/" + fileName + ".png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		setDrawable(new TextureRegionDrawable(tex));
		setScaling(Scaling.fit);
		float size = 0.07f * RainbowSeedGame.WORLD_HEIGHT;
		setSize(size, size);

		dragAndDrop = new DragAndDrop();
		dragAndDropTargets = new EnumMap<>(ItemType.class);
		setupDragAndDrop();
	}

	public void removeCombo(ItemType type) {
		remainingCombinations.remove(type);
	}

	private void setupDragAndDrop() {	
		dragAndDrop.setTapSquareSize(0);
		dragAndDrop.setDragActorPosition(getWidth()/2f, -getHeight()/2f);
		dragAndDrop.addSource(new Source(this) {

			@Override
			public Payload dragStart(InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject(DraggableItem.this);

				payload.setDragActor(getActor());

				DraggableItem.this.setTouchable(Touchable.disabled);

				return payload;
			}

			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
				super.dragStop(event, x, y, pointer, payload, target);
				if (isVisible()) {
					DraggableItem.this.setTouchable(Touchable.enabled);
				} else {
					// Was dropped in the summoning circle, don't go touchable again
					DraggableItem.this.setVisible(true);
				}
				DraggableItem.this.toFront();
			}

		});
	}

	public void addDropTarget(DraggableItem itemToDropOn) { 
		Target target = new Target(itemToDropOn) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				payload.setValidDragActor(new PossibleComboHint(itemToDropOn));
				return true;
			}

			public void reset (Source source, Payload payload) {
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				DraggableItem draggedItem= (DraggableItem)payload.getObject();
				Combiner.combineItems(draggedItem, itemToDropOn);
			}
		};
		dragAndDrop.addTarget(target);
		dragAndDropTargets.put(itemToDropOn.getType(), target);
	}

	public void addSummoningCircleDropTarget(SummoningCircle circle) { 
		dragAndDrop.addTarget(new Target(circle) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				circle.hintDroppable();
				return true;
			}

			public void reset (Source source, Payload payload) {
				circle.unhintDroppable();
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				DraggableItem draggedItem= (DraggableItem)payload.getObject();
				circle.acceptItem(draggedItem);
			}
		});
	}

	public void removeDropTarget(ItemType itemType) {
		Target target = dragAndDropTargets.remove(itemType);
		System.out.println(type + " removing contact with " + itemType);
		dragAndDrop.removeTarget(target);
	}

	@Override
	public String toString() {
		return name;
	}

	public ItemType getType() {
		return type;
	}

	public boolean hasRemainingCombinations() {
		return !remainingCombinations.isEmpty();
	}

	public void removeItem(float delay) {
		Label allCombosFoundLabel = UI.makeLabelWithBackground("All combinations with "+ toString().toUpperCase() + " found!");
		allCombosFoundLabel.setX(RainbowSeedGame.WORLD_WIDTH * 0.01f);
		allCombosFoundLabel.setVisible(false);
		allCombosFoundLabel.addAction(Actions.sequence(
				Actions.delay(delay),
				Actions.visible(true),
				Actions.parallel(
						Actions.moveBy(0, RainbowSeedGame.WORLD_HEIGHT * 0.15f, 5f, Interpolation.circleOut),
						Actions.delay(3f, Actions.fadeOut(0.3f))
						),
				Actions.removeActor()
				));
		getStage().addActor(allCombosFoundLabel);
		remove();
	}

	@Override
	public void spring() {
		this.setVisible(type.getValidSeasons().contains(Season.SPRING));
	}

	@Override
	public void summer() {
		this.setVisible(type.getValidSeasons().contains(Season.SUMMER));
	}

	@Override
	public void autumn() {
		this.setVisible(type.getValidSeasons().contains(Season.AUTUMN));
	}

	@Override
	public void winter() {
		this.setVisible(type.getValidSeasons().contains(Season.WINTER));
		if(type==ItemType.AXE&&this.getX()<0) {
			this.setPosition(MathUtils.random(this.getStage().getWidth()-this.getWidth()), MathUtils.random(this.getStage().getHeight()-this.getHeight()));
		}
	}

	@Override
	public void rainbow() {
		// TODO Auto-generated method stub
	}

}
