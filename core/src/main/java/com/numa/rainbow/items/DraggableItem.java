package com.numa.rainbow.items;

import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.ui.PossibleComboHint;

public class DraggableItem extends Image {

	private final DragAndDrop dragAndDrop;
	private String name;
	private Set<ItemType> remainingCombinations;

	private ItemType type;

	public DraggableItem(String fileName, ItemType type,Set<ItemType> combos) {
		this.type=type;
		this.remainingCombinations=combos;
		this.name = fileName;

		Texture tex = new Texture(Gdx.files.internal("items/" + fileName + ".png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		setDrawable(new TextureRegionDrawable(tex));
		setScaling(Scaling.fit);
		float size = 0.07f * RainbowSeedGame.WORLD_HEIGHT;
		setSize(size, size);
		
		dragAndDrop = new DragAndDrop();
		setupDragAndDrop();
	}
	
	public void removeCombo(ItemType type) {
		remainingCombinations.remove(type);
	}

	private void setupDragAndDrop() {	
		dragAndDrop.setTapSquareSize(0);
		dragAndDrop.setDragActorPosition(getWidth()/2f, -getHeight()/2f);
		dragAndDrop.addSource(new Source(this) {

			private Payload payload;

			@Override
			public Payload dragStart(InputEvent event, float x, float y, int pointer) {
				payload = new Payload();
				payload.setObject(DraggableItem.this);

				payload.setDragActor(getActor());
				
				DraggableItem.this.setTouchable(Touchable.disabled);

				return payload;
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				payload.setValidDragActor(new PossibleComboHint(DraggableItem.this));
				super.drag(event, x, y, pointer);
			}

			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
				super.dragStop(event, x, y, pointer, payload, target);
				DraggableItem.this.setTouchable(Touchable.enabled);
			}

		});
	}
	public void addDropTarget(DraggableItem target) { 
		dragAndDrop.addTarget(new Target(target) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				return true;
			}

			public void reset (Source source, Payload payload) {
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				DraggableItem draggedItem= (DraggableItem)payload.getObject();
				Combiner.combineItems(draggedItem, target);;
			}
		});

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

}
