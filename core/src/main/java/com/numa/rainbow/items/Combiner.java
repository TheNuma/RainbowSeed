package com.numa.rainbow.items;
import java.util.function.Function;

import com.numa.rainbow.ui.UIStage;

public class Combiner {
	private static ItemInteractions interactions;
	private static Function<ItemType,DraggableItem> typeToDraggable;
	private static boolean isAutumnUnlocked=false;
	public static UIStage uiStage; 
	
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
    		
    		if(type3==ItemType.VINE) {
    			uiStage.getSidebar().setVisible(true);
    			uiStage.getSpringButton().setVisible(true);
    			uiStage.getSummerButton().setVisible(true);
    			//unlock summer
    		}
    		else if(type3==ItemType.PLUMTREE) {
    			uiStage.getWinterButton().setVisible(true);
    			//unlock Winter
    		}
    		else if((type3==ItemType.DAFFODIL||type3==ItemType.GREENBEANS||type3==ItemType.BLUEBERRY)&&!isAutumnUnlocked) {
    			isAutumnUnlocked=true;
    			uiStage.getAutumnButton().setVisible(true);
    			//unlock fall
    		}
    		
    		//remove 'parent' objects if necessary
    		float uiDelay = 0.5f;
    		boolean firstRemoved = checkAndRemoveFullyUsedItems(item1, uiDelay);
    		if (firstRemoved) {
    			uiDelay = 3f;
    		}
    		checkAndRemoveFullyUsedItems(item2, uiDelay);
    	}
    	else {
    		System.out.println("No combinations found between "+item1+ " and "+item2);
    	}
    }
	public static void setItemInteractions(ItemInteractions interactions, Function<ItemType,DraggableItem> typeToDraggable) {
		Combiner.interactions=interactions;
		Combiner.typeToDraggable=typeToDraggable;
	}
	public static void setUIelements(UIStage uiStage) {
		Combiner.uiStage=uiStage;
	}
    public static boolean checkAndRemoveFullyUsedItems(DraggableItem t, float uiDelay) {
    	if (!t.hasRemainingCombinations())
    	{
    		System.out.println("All combinations with "+t.getType().toString()+" has been found");
    		t.removeItem(uiDelay);
    		return true;
    	}
    	return false;
    }

	
}
