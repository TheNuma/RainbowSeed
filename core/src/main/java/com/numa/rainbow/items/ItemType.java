package com.numa.rainbow.items;

public enum ItemType {
	SEED(true,"sesame", "Seed"),
	DIRT(true,"dirt","Dirt"),
	GRASS(true,"grass","Grass"),
	WATER(false,"watering-can","Water");
//	SUN(false),
//	SEAWEED(true),
//	DANDELION(true),
//	WHEAT(true),
//	REED(false);

	private boolean isSingleUse;
	private String fileName, itemName;

	ItemType(boolean isSingleUse, String fileName, String itemName) {
		this.isSingleUse = isSingleUse;
		this.fileName=fileName;
		this.itemName=itemName;
	}

	public boolean isSingleUse() {
		return isSingleUse;
	}

	public String getFileName() {
		return fileName;
	}

	public String getItemName() {
		return itemName;
	}

}
