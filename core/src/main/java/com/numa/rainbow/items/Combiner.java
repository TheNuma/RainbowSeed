package com.numa.rainbow.items;
import java.util.function.Function;

import com.numa.rainbow.ui.Farm;
import com.numa.rainbow.ui.UI;

public class Combiner {
	private static ItemInteractions interactions;
	private static Function<ItemType,DraggableItem> typeToDraggable;
	public static void combineItems(DraggableItem item1, DraggableItem item2) {
		
       	if (interactions.hasCombinations(item1.getType(), item2.getType())) {
    		//create new object
    		ItemType type3 = interactions.getCombination(item1.getType(), item2.getType());        		
    		System.out.println(item1.getType()+" and "+item2.getType()+ " combined to make "+type3);
    		DraggableItem spawnItem = typeToDraggable.apply(type3);
    		spawnItem.setPosition((item1.getX()+item2.getX())/2f, (item1.getY()+item2.getY())/2f);
    		spawnItem.setVisible(true);
    		item1.removeCombo(type3);
    		item2.removeCombo(type3);
    		//remove 'parent' objects if necessary
    		checkAndRemoveSingleUse(item1);
    		checkAndRemoveSingleUse(item2);
    	}
    	else {
    		System.out.println("No combinations found between "+item1+ " and "+item2);
    	}
    }
	public static void setItemInteractions(ItemInteractions interactions, Function<ItemType,DraggableItem> typeToDraggable) {
		Combiner.interactions=interactions;
		 Combiner.typeToDraggable=typeToDraggable;
	}
    public static void checkAndRemoveSingleUse(DraggableItem t) {
    	if (!t.hasRemainingCombinations())
    	{
    		System.out.println("All combinations with "+t.getType().toString()+" has been found");
    		t.remove();
    	}
    }
}
