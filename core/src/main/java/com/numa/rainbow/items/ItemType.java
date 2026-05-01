package com.numa.rainbow.items;

public enum ItemType {
	SEED("sesame","Seed"),
	DIRT("dirt","Dirt"),
	GRASS("grass","Grass"),
	WATER("watering-can","Water");
//	SUN(false),
//	SEAWEED(true),
//	DANDELION(true),
//	WHEAT(true),
//	REED(false);

	private String fileName, itemName;

	ItemType(String fileName, String itemName) {
		this.fileName=fileName;
		this.itemName=itemName;
	}
	public String getFileName() {
		return fileName;
	}

	public String getItemName() {
		return itemName;
	}

}
