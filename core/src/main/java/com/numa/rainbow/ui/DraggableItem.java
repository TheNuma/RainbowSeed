package com.numa.rainbow.ui;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.numa.rainbow.items.Combiner;
import com.numa.rainbow.items.ItemType;

public class DraggableItem extends Table {

	private final DragAndDrop dragAndDrop;
	private Image image;
	private String name;
	private Set<ItemType> remainingCombinations;

	private ItemType type;

	
	public DraggableItem(String fileName, Label label,ItemType type,Set<ItemType> combos) {


		this.type=type;
		this.remainingCombinations=combos;
		this.name = fileName;
		setTouchable(Touchable.enabled);

		add(label).growX();
		label.setWrap(true);
		label.setAlignment(Align.center);
		label.setTouchable(Touchable.disabled);
		row();

		Texture tex = new Texture(Gdx.files.internal("items/" + fileName + ".png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		image = new Image(tex);
		image.setScaling(Scaling.fit);
		image.setTouchable(Touchable.disabled);
		add(image);
		setSize(75, 100);
		
		dragAndDrop = new DragAndDrop();
		setupDragAndDrop();
		
	}
	
	public void removeCombo(ItemType type) {
		remainingCombinations.remove(type);
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
		image.setColor(color);
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

				Label validLabel = UI.makeLabel("Valid combo!");
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				Label invalidLabel = UI.makeLabel("Some payload!");
				invalidLabel.setColor(1, 0, 0, 1);
				payload.setInvalidDragActor(invalidLabel);

				DraggableItem.this.setTouchable(Touchable.disabled);

				return payload;
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				payload.setValidDragActor(new PossibleComboHint());
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
				getActor().setColor(Color.GREEN);
				return true;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				System.out.println(payload.getObject() + " landed on " + target.toString() + " at: "+ x + ", " + y);
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
