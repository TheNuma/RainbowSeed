package com.numa.rainbow.items;

import java.util.Set;

import com.numa.rainbow.season.Season;

public enum ItemType {
	SUMMERSEED("sesame","Seeds",Season.summerOnly),
	DIRT("dirt","Dirt",Season.allSeasons),
	WEED("broken-pottery","Weeds",Season.notWinter),//FIX
	GRASS("grass","Grass",Season.allSeasons),
	WATER("watering-can","Water",Season.allSeasons),
	ROCK("rock","Rock",Season.allSeasons),
	SUN("sun","Sun",Season.summerOnly),	
	SNOW("snow","Snow",Season.allSeasons),
	SEAWATER("wave-crest","Sea Water",Season.allSeasons),
	STRAWBERRY("strawberry","Strawberry",Season.allSeasons),
	FALLSEED("seasonal-seed","Seeds",Season.autumnOnly),
	SPRINGSEED("bulb","Seeds",Season.springOnly),
	WINTERSEED("ground-sprout","Seeds",Season.winterOnly),
	BAMBOO("bamboo","Bamboo",Season.allSeasons),
	BUSH("broken-pottery","Bush",Season.allSeasons),//FIX
	WHEAT("wheat","Wheat",Season.allSeasons),
	SCARECROW("scarecrow","Scarecrow",Season.autumnOnly),
	TREE("oak","Tree",Season.allSeasons),
	AXE("axe","Axe",Season.allSeasons),
	STICK("stick","Stick",Season.allSeasons),
	TULIP("broken-pottery","Tulip",Season.allSeasons),//FIX
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
	PLUMTREE("broken-pottery","Plum Tree",Season.allSeasons),//FIX
	ROSE("rose","Red Rose",Season.allSeasons),
	ORANGE_TREE("fruit-tree","Orange Tree",Season.allSeasons),
	DAFFODIL("dafffodil","Yellow Daffodil",Season.allSeasons),
	GREENBEANS("beans","Green Beans",Season.allSeasons),
	BLUEBERRY("blueberry","Blue Berries",Season.allSeasons),
	INDIGO("broken-pottery","Indigo Flower",Season.allSeasons),//FIX
	VIOLET("violets","Violets",Season.allSeasons),
	RAINBOWSEED("broken-pottery","RAINBOW SEED",Season.allSeasons);//FIX

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
