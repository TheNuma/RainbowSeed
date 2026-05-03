package com.numa.rainbow.cutscenes;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.numa.rainbow.season.Season;
import com.numa.rainbow.ui.UI;

public class BeginTheEnd extends Cutscene {

	private Season currentSeason;

	public BeginTheEnd(Runnable endCutscene, Season currentSeason) {
		super(endCutscene);
		this.currentSeason = currentSeason;
	}
	@Override
	protected void startCutscene() {
		String text1 = UI.color(UI.DARK_BLUE, "You have found all 7 of the colorful\nplants and are now ready to craft\nthe");
		text1 = addWordRainbow(text1);
		text1 += UI.color(UI.DARK_BLUE, "seed.");
		Runnable next = makeNextButtonRunnable(this::text2);
		makeSpeechBubble(text1, next);
		addAction(Actions.delay(3f, Actions.run(next)));
	}

	private void text2() {
		String prefix = "Shift one final time to Spring and";
		if (currentSeason == Season.SPRING) {
			prefix = "Finally, here in Spring you can";
		}
		String text2 = UI.color(UI.DARK_BLUE, prefix + "\ncomplete the ritual.");
		Runnable start = makeFinalButtonRunnable("It's time.", () -> {});
		makeSpeechBubble(text2, start);
		addAction(Actions.delay(3f, Actions.run(start)));
	}

}
