package com.numa.rainbow.items;
import java.util.*;
import java.util.function.Function;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.numa.rainbow.audio.RainbowAudioManager;
import com.numa.rainbow.ui.UIStage;

public class Combiner {
	private static ItemInteractions interactions;
	private static Function<ItemType,DraggableItem> typeToDraggable;
	private static boolean isAutumnUnlocked=false;
	public static UIStage uiStage; 
	
	public static void combineItems(DraggableItem item1, DraggableItem item2) {		
       	if (interactions.hasCombinations(item1.getType(), item2.getType())) {
			List<DraggableItem> remainingItems = new ArrayList<>();
			
    		//create new object
    		ItemType type3 = interactions.getCombination(item1.getType(), item2.getType());
    		removeCombination(item1, item2);
    		System.out.println(item1.getType()+" and "+item2.getType()+ " combined to make "+type3);
    		
    		DraggableItem spawnItem = typeToDraggable.apply(type3);
    		spawnItem.setPosition((item1.getX()+item2.getX())/2f, (item1.getY()+item2.getY())/2f);
    		spawnItem.setVisible(true);
    		spawnItem.addAction(Actions.delay(0.1f, Actions.run(() -> spawnItem.toFront())));
    		item1.removeCombo(type3);
    		item2.removeCombo(type3);
    		
    		if(type3==ItemType.VINE) {
    			uiStage.unlockSummerCutscene();
    		}
    		else if(type3==ItemType.PLUMTREE) {
    			uiStage.unlockWinterCutscene();
    		}
    		else if((type3==ItemType.ROSE||type3==ItemType.DAFFODIL||type3==ItemType.GREENBEANS||type3==ItemType.BLUEBERRY||
    				type3==ItemType.ORANGE_TREE||type3==ItemType.INDIGO||type3==ItemType.VIOLET)&&!isAutumnUnlocked) {
    			isAutumnUnlocked=true;
    			uiStage.unlockAutumnCutscene();
    		} else if (interactions.getAllCombinations().isEmpty()) {
    			uiStage.beginTheEnd();
    		}
    		
			remainingItems.add(spawnItem);
			
    		//remove 'parent' objects if necessary
    		float uiDelay = 0.5f;
    		boolean firstRemoved = checkAndRemoveFullyUsedItems(item1, uiDelay);
    		if (firstRemoved) {
    			uiDelay = 3f;
			} else {
				remainingItems.add(item1);
			}
    		
    		boolean secondRemoved = checkAndRemoveFullyUsedItems(item2, uiDelay);
    		if(!secondRemoved) {
    			remainingItems.add(item2);
    		}
			pushAwayRemainingItems(remainingItems, new Vector2(spawnItem.getX(), spawnItem.getY()));

			RainbowAudioManager.playComboSound();
    	}
    	else {
    		System.out.println("No combinations found between "+item1+ " and "+item2);
    	}
    }
	
	private static void removeCombination(DraggableItem item1, DraggableItem item2) {
		item1.removeDropTarget(item2.getType());
		item2.removeDropTarget(item1.getType());
		interactions.markCombinationComplete(item1.getType(), item2.getType());
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
    
	private static void pushAwayRemainingItems(List<DraggableItem> remainingItems, Vector2 center) {
		Random random = new Random();

		float angle = random.nextFloat() * 360f;
		int items = remainingItems.size();

		for (Iterator<DraggableItem> it = remainingItems.iterator(); it.hasNext();) {
			DraggableItem draggableItem = it.next();
			MoveToAction move = new MoveToAction();

			float distance = random.nextFloat() * 50 + 75;
			move.setDuration(0.5f + random.nextFloat() * 0.2f);
			move.setStartPosition(center.x, center.y);
			move.setInterpolation(Interpolation.exp5Out);
			float destinationX = draggableItem.getX() + MathUtils.sinDeg(angle) * distance;
			float destinationY = draggableItem.getY() + MathUtils.cosDeg(angle) * distance;
			destinationX = MathUtils.clamp(destinationX, 0, draggableItem.getStage().getWidth() - draggableItem.getWidth());
			destinationY = MathUtils.clamp(destinationY, 0, draggableItem.getStage().getHeight() - draggableItem.getHeight());
			move.setPosition(destinationX, destinationY);
			draggableItem.addAction(move);

			angle += 360f / items;
			angle += random.nextFloat() * 20;
		}

	}
}
