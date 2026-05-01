package com.numa.rainbow.items;

import java.util.HashSet;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Item extends Actor{
	private HashSet<ItemType> remainingCombinations;

	private ItemType type;
	private boolean hasRemainingCombinations;
	
	public Item(ItemType type,HashSet combos) {
		this.type=type;
		this.remainingCombinations=combos;
		this.hasRemainingCombinations=true;
		this.setSize(40, 40);
		this.setPosition(new Random().nextInt(500),new Random().nextInt(500));
		this.debug();
	}
	
	public void removeCombo(ItemType type) {
		remainingCombinations.remove(type);
		if(remainingCombinations.isEmpty()) {
			hasRemainingCombinations= false;
		}
	}
	
	public ItemType getType() {
		return type;
	}
	public boolean hasRemainingCombinations() {
		return hasRemainingCombinations;
	}
}
