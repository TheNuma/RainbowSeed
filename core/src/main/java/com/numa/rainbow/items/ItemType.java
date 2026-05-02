package com.numa.rainbow.items;

public enum ItemType {
	SUMMERSEED("sesame","Seeds"),
	DIRT("dirt","Dirt"),
	WEED("broken-pottery","Weeds"),//FIX
	GRASS("grass","Grass"),
	WATER("watering-can","Water"),
	ROCK("rock","Rock"),
	SUN("sun","Sun"),	
	SNOW("snow","Snow"),
	SEAWATER("wave-crest","Sea Water"),
	STRAWBERRY("strawberry","Strawberry"),
	FALLSEED("seasonal-seed","Seeds"),
	SPRINGSEED("bulb","Seeds"),
	WINTERSEED("ground-sprout","Seeds"),
	BAMBOO("bamboo","Bamboo"),
	BUSH("broken-pottery","Bush"),//FIX
	WHEAT("wheat","Wheat"),
	SCARECROW("scarecrow","Scarecrow"),
	TREE("oak","Tree"),
	AXE("axe","Axe"),
	STICK("stick","Stick"),
	TULIP("broken-pottery","Tulip"),//FIX
	BERRYBUSH("berry-bush","Berry Bush"),
	VINE("vines","Vines"),
	BRAMBLES("brambles","Brambles"),
	PUMPKIN("pumpkin","Pumpkins"),
	SNOWDROP("snowdrop","Snowdrop"),
	DANDELION("dandelion","Dandelion"),
	REED("reed","Reed"),
	FLAX("flax","Flax"),
	HOPS("hops","Hops"),	
	SEAWEED("seaweed","Seaweed"),
	CACTUS("cactus","Cactus"),
	PLUMTREE("broken-pottery","Plum Tree"),//FIX
	ROSE("rose","Red Rose"),
	ORANGE_TREE("fruit-tree","Orange Tree"),
	DAFFODIL("dafffodil","Yellow Daffodil"),
	GREENBEANS("beans","Green Beans"),
	BLUEBERRY("blueberry","Blue Berries"),
	INDIGO("broken-pottery","Indigo Flower"),//FIX
	VIOLET("violets","Violets"),
	RAINBOWSEED("broken-pottery","RAINBOW SEED");//FIX

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
