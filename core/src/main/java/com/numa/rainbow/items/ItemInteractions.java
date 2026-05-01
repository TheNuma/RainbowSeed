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
//		addCombination(DIRT, SEED, DANDELION);
//		addCombination(SEED, WATER, REED );
//		addCombination(REED, WATER,SEAWEED );
//		addCombination(SUN, GRASS, WHEAT);
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
	
	


}
