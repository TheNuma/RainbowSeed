package com.numa.rainbow.items;

import java.util.Set;

import com.numa.rainbow.season.Season;

public enum ItemType {
	SUMMERSEED("sesame","Seeds",Season.summerOnly),
	DIRT("dirt","Dirt",Season.allSeasons),
	WEED("evil-bud","Weeds",Season.notWinter),
	GRASS("grass","Grass",Season.allSeasons),
	WATER("watering-can","Water",Season.allSeasons),
	ROCK("rock","Rock",Season.winterOnly),
	SUN("sun","Sun",Season.summerOnly),	
	SNOW("snow","Snow",Season.winterOnly),
	SEAWATER("wave-crest","Sea Water",Season.allSeasons),
	STRAWBERRY("strawberry","Strawberry",Season.allSeasons),
	FALLSEED("seasonal-seed","Seeds",Season.autumnOnly),
	SPRINGSEED("bulb","Seeds",Season.springOnly),
	WINTERSEED("ground-sprout","Seeds",Season.winterOnly),
	BAMBOO("bamboo","Bamboo",Season.allSeasons),
	BUSH("bush","Bush",Season.allSeasons),
	WHEAT("wheat","Wheat",Season.allSeasons),
	SCARECROW("scarecrow","Scarecrow",Season.autumnOnly),
	TREE("oak","Tree",Season.allSeasons),
	AXE("axe","Axe",Season.allSeasons),
	STICK("stick","Stick",Season.allSeasons),
	TULIP("tulip","Tulip",Season.allSeasons),
	BERRYBUSH("berry-bush","Berry Bush",Season.allSeasons),
	VINE("vines","Vines",Season.allSeasons),
	BRAMBLES("brambles","Brambles",Season.allSeasons),
	PUMPKIN("pumpkin","Pumpkins",Season.allSeasons),
	SNOWDROP("snowdrop","Snowdrop",Season.allSeasons),
	DANDELION("dandelion","Dandelion",Season.allSeasons),
	REED("reed","Reed",Season.allSeasons),
	FLAX("flax","Flax",Season.allSeasons),
	HOPS("hops","Hops",Season.allSeasons),	
	SEAWEED("seaweed","Seaweed",Season.allSeasons),
	CACTUS("cactus","Cactus",Season.allSeasons),
	PLUMTREE("fruit-tree","Plum Tree",Season.allSeasons),
	ROSE("rose","Red Rose",Season.allSeasons),
	ORANGE_TREE("orange-tree","Orange Tree",Season.allSeasons),
	DAFFODIL("daffodil","Yellow Daffodil",Season.allSeasons),
	GREENBEANS("beans","Green Beans",Season.allSeasons),
	BLUEBERRY("blueberry","Blue Berries",Season.allSeasons),
	INDIGO("indigo","Indigo Flower",Season.allSeasons),
	VIOLET("violets","Violets",Season.allSeasons),
	RAINBOWSEED("rainbow-seed","RAINBOW SEED",Season.allSeasons);

	private String fileName, itemName;
	private Set<Season>season;
	

	ItemType(String fileName, String itemName, Set<Season>season) {
		this.fileName=fileName;
		this.itemName=itemName;
		this.season=season;
	}
	public String getFileName() {
		return fileName;
	}

	public String getItemName() {
		return itemName;
	}

	public Set<Season> getValidSeasons() {
		return season;
	}
}
