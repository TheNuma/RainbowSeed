package com.numa.rainbow.items;
import com.numa.rainbow.ui.DraggableItem;
import com.numa.rainbow.ui.UI;

public class Combiner {
	private static ItemInteractions interactions=new ItemInteractions();
	
	public static void combineItems(DraggableItem item1, DraggableItem item2) {
		
       	if (interactions.hasCombinations(item1.getType(), item2.getType())) {
    		//create new object
    		ItemType type3 = interactions.getCombination(item1.getType(), item2.getType());        		
    		System.out.println(item1.getType()+" and "+item2.getType()+ " combined to make "+type3);
    		DraggableItem spawnItem = UI.makeDraggableItem(type3);
    		item1.getStage().addActor(spawnItem);
    		spawnItem.setPosition((item1.getX()+item2.getX())/2f, (item1.getY()+item2.getY())/2f);
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
    
    public static void checkAndRemoveSingleUse(DraggableItem t) {
    	if (t.getType().isSingleUse()&&!t.hasRemainingCombinations())
    	{
    		System.out.println("All combinations with "+t.getType().toString()+" has been found");
    		t.remove();
    	}
    }
}
