package com.numa.rainbow.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.season.Season;
import com.numa.rainbow.season.SeasonShifter;

public class UIStage extends Stage {

	public UIStage(Viewport viewport, SeasonShifter seasonShifter) {
		super(viewport);

		Table sidebar = new Table();
		sidebar.setBackground(UI.getUISidebarTexture());
		sidebar.defaults().spaceTop(RainbowSeedGame.WORLD_HEIGHT * 0.1f).spaceBottom(RainbowSeedGame.WORLD_HEIGHT * 0.1f);

		sidebar.add(UI.getSeasonButton(Season.SPRING, () -> seasonShifter.setSeason(Season.SPRING)));
		sidebar.row();
		sidebar.add(UI.getSeasonButton(Season.SUMMER, () -> seasonShifter.setSeason(Season.SUMMER)));
		sidebar.row();
		sidebar.add(UI.getSeasonButton(Season.AUTUMN, () -> seasonShifter.setSeason(Season.AUTUMN)));
		sidebar.row();
		sidebar.add(UI.getSeasonButton(Season.WINTER, () -> seasonShifter.setSeason(Season.WINTER)));
		sidebar.row();
		

		Table sidebarPaddingTable = new Table();
		sidebarPaddingTable.padRight(RainbowSeedGame.WORLD_WIDTH * 0.0075f);
		sidebarPaddingTable.padTop(sidebarPaddingTable.getPadRight());
		sidebarPaddingTable.padBottom(sidebarPaddingTable.getPadRight());
		
		sidebarPaddingTable.setSize(RainbowSeedGame.UI_WIDTH_FRACTION * RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		sidebarPaddingTable.setPosition(RainbowSeedGame.WORLD_WIDTH - sidebarPaddingTable.getWidth()*0.75f, 0);
		sidebarPaddingTable.add(sidebar).grow().padRight(RainbowSeedGame.WORLD_WIDTH * 0.01f);
		addActor(sidebarPaddingTable);
	}
}
