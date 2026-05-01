package com.numa.rainbow.items;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Item extends Actor{
	private ItemType type;
	
	public Item(ItemType type) {
		this.type=type;
		this.setSize(40, 40);
		this.setPosition(new Random().nextInt(500),new Random().nextInt(500));
		this.debug();
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}
}
