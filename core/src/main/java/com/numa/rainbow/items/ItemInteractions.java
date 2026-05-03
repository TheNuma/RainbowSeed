package com.numa.rainbow.items;

import static com.numa.rainbow.items.ItemType.AXE;
import static com.numa.rainbow.items.ItemType.BAMBOO;
import static com.numa.rainbow.items.ItemType.BERRYBUSH;
import static com.numa.rainbow.items.ItemType.BLUEBERRY;
import static com.numa.rainbow.items.ItemType.BRAMBLES;
import static com.numa.rainbow.items.ItemType.BUSH;
import static com.numa.rainbow.items.ItemType.CACTUS;
import static com.numa.rainbow.items.ItemType.DAFFODIL;
import static com.numa.rainbow.items.ItemType.DANDELION;
import static com.numa.rainbow.items.ItemType.DIRT;
import static com.numa.rainbow.items.ItemType.FALLSEED;
import static com.numa.rainbow.items.ItemType.FLAX;
import static com.numa.rainbow.items.ItemType.GRASS;
import static com.numa.rainbow.items.ItemType.GREENBEANS;
import static com.numa.rainbow.items.ItemType.HOPS;
import static com.numa.rainbow.items.ItemType.INDIGO;
import static com.numa.rainbow.items.ItemType.ORANGE_TREE;
import static com.numa.rainbow.items.ItemType.PLUMTREE;
import static com.numa.rainbow.items.ItemType.PUMPKIN;
import static com.numa.rainbow.items.ItemType.REED;
import static com.numa.rainbow.items.ItemType.ROCK;
import static com.numa.rainbow.items.ItemType.ROSE;
import static com.numa.rainbow.items.ItemType.SCARECROW;
import static com.numa.rainbow.items.ItemType.SEAWATER;
import static com.numa.rainbow.items.ItemType.SEAWEED;
import static com.numa.rainbow.items.ItemType.SNOW;
import static com.numa.rainbow.items.ItemType.SNOWDROP;
import static com.numa.rainbow.items.ItemType.SPRINGSEED;
import static com.numa.rainbow.items.ItemType.STICK;
import static com.numa.rainbow.items.ItemType.STRAWBERRY;
import static com.numa.rainbow.items.ItemType.SUMMERSEED;
import static com.numa.rainbow.items.ItemType.SUN;
import static com.numa.rainbow.items.ItemType.TREE;
import static com.numa.rainbow.items.ItemType.TULIP;
import static com.numa.rainbow.items.ItemType.VINE;
import static com.numa.rainbow.items.ItemType.WATER;
import static com.numa.rainbow.items.ItemType.WEED;
import static com.numa.rainbow.items.ItemType.WHEAT;
import static com.numa.rainbow.items.ItemType.WINTERSEED;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemInteractions {
	
 
private final Map<CombinationKey, ItemType> combinations;
	public ItemInteractions() {
		this.combinations=new HashMap<CombinationKey, ItemType>();
		addCombination(DIRT, WATER, GRASS);
		addCombination(WATER,ROCK,SEAWATER);
		addCombination(DIRT,GRASS,BUSH);
		addCombination(BAMBOO,BUSH,TREE);
		addCombination(GRASS,SUN,WHEAT);
		addCombination(TREE,AXE,STICK);
		addCombination(BUSH,STRAWBERRY,BERRYBUSH);
		addCombination(WEED,TREE,VINE);
		addCombination(WEED,CACTUS,BRAMBLES);
		addCombination(SCARECROW,AXE,PUMPKIN);
		addCombination(GRASS,SNOW,SNOWDROP);
		addCombination(WEED,SUN,DANDELION);
		addCombination(WHEAT,WATER,REED);
		addCombination(TULIP,VINE,HOPS);
		addCombination(SEAWATER,WEED,SEAWEED);
		addCombination(BRAMBLES,SUN,CACTUS);
		addCombination(TREE,BERRYBUSH,PLUMTREE);
		addCombination(BRAMBLES,TULIP,ROSE);
		addCombination(PUMPKIN,TREE,ORANGE_TREE);
		addCombination(SNOWDROP,DANDELION,DAFFODIL);
		addCombination(REED,ROCK,GREENBEANS);
		addCombination(FLAX,BERRYBUSH,BLUEBERRY);
		addCombination(HOPS,SEAWEED,INDIGO);
		
		//change these maybe?
		addCombination(FALLSEED,DIRT,TULIP);
		addCombination(SPRINGSEED,DIRT,BAMBOO);
		addCombination(WINTERSEED,DIRT,FLAX);
		addCombination(SUMMERSEED,DIRT,STRAWBERRY);
		
		//RAINBOWSEED
	}
	private void addCombination(ItemType type1 , ItemType type2, ItemType type3 ) {
		CombinationKey key=new CombinationKey(type1, type2);
		combinations.put(key, type3);
	}
	
	public void markCombinationComplete(ItemType type1 , ItemType type2) {
		CombinationKey key=new CombinationKey(type1, type2);
		combinations.remove(key);
	}
	
	public ItemType getCombination(ItemType type1, ItemType type2) {
		CombinationKey key= new CombinationKey(type1, type2);
		return combinations.get(key);
	}
	
	public boolean hasCombinations(ItemType type1, ItemType type2) {
		CombinationKey key= new CombinationKey(type1, type2);
		return combinations.containsKey(key);
	}
	
	public Set<ItemType> getCombinationsFor(ItemType type){
		Set<CombinationKey> allCombos = combinations.keySet();
		Set<ItemType> product=new HashSet<>();
		for(CombinationKey c:allCombos) {
			if(c.getType1().equals(type)||c.getType2().equals(type)) {
				product.add(combinations.get(c));
			}
		}
		return product;	
	}
	
	public Set<CombinationKey> getAllCombinations(){
		return combinations.keySet();
	}
	


}
