package com.numa.rainbow.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.numa.rainbow.RainbowSeedGame;

public class UIStage extends Stage {

	public UIStage(Viewport viewport) {
		super(viewport);

		Table sidebar = new Table();
		sidebar.setBackground(UI.getUISidebarTexture());
		sidebar.setColor(Color.BROWN);
		sidebar.setColor(sidebar.getColor().r, sidebar.getColor().g, sidebar.getColor().b, 0.75f);
		
		sidebar.add(UI.getSpringButton(() -> System.out.println("CHONGING TO SPRING")));
		

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
