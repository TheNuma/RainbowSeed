package com.numa.rainbow.items;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.numa.rainbow.items.ItemType.*;

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
