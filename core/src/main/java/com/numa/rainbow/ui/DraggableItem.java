package com.numa.rainbow.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;

public class DraggableItem extends Table {

	public DraggableItem() {
		super();
		setTransform(true);
		setTouchable(Touchable.enabled);
		
		Image image = new Image(new Texture(Gdx.files.internal("items/sesame.png")));
		add(image);
		
		DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.addSource(new Source(this) {

			@Override
			public Payload dragStart(InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject("MY sesamees!");
				
				payload.setDragActor(getActor());
				
				Label validLabel = UI.makeLabel("Some payload!");
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				Label invalidLabel = UI.makeLabel("Some payload!");
				invalidLabel.setColor(1, 0, 0, 1);
				payload.setInvalidDragActor(invalidLabel);
				
				
				return payload;
			}
		
		});
	}

}
