package com.numa.rainbow.items;

public enum ItemType {
SEED(true),DIRT(true),GRASS(true),WATER(false),SUN(false),SEAWEED(true),DANDELION(true),WHEAT(true),REED(false);
	
	private boolean isSingleUse;
	
	ItemType(boolean isSingleUse) {
		this.isSingleUse = isSingleUse;
	}

	public boolean isSingleUse() {
		return isSingleUse;
	}
	
}
