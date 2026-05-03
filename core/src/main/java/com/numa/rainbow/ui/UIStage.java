package com.numa.rainbow.ui;

import java.util.List;
import java.util.function.Supplier;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.numa.rainbow.RainbowSeedGame;
import com.numa.rainbow.audio.RainbowAudioManager;
import com.numa.rainbow.cutscenes.*;
import com.numa.rainbow.items.DraggableItem;
import com.numa.rainbow.season.Season;
import com.numa.rainbow.season.SeasonShifter;

public class UIStage extends Stage {

	private Button springButton;
	private Button summerButton;
	private Button autumnButton;
	private Button winterButton;	
	private Table sidebar;

	private Stage gameStage;
	private SeasonShifter seasonShifter;
	private Supplier<List<DraggableItem>> colorfulItems;
	private RainbowAudioManager audio;

	public UIStage(Viewport viewport, SeasonShifter seasonShifter, Stage gameStage, Supplier<List<DraggableItem>> colorfulItems, RainbowAudioManager audio) {
		super(viewport);
		this.seasonShifter = seasonShifter;
		this.gameStage = gameStage;
		this.colorfulItems = colorfulItems;
		this.audio = audio;

		sidebar = new Table();
		sidebar.setBackground(UI.getUISidebarTexture());
		sidebar.defaults().spaceTop(RainbowSeedGame.WORLD_HEIGHT * 0.1f).spaceBottom(RainbowSeedGame.WORLD_HEIGHT * 0.1f);

		springButton=UI.getSeasonButton(Season.SPRING, () -> seasonShifter.setSeason(Season.SPRING));
		sidebar.add(springButton);
		sidebar.row();
		summerButton=UI.getSeasonButton(Season.SUMMER, () -> seasonShifter.setSeason(Season.SUMMER));
		sidebar.add(summerButton);
		sidebar.row();
		autumnButton=UI.getSeasonButton(Season.AUTUMN, () -> seasonShifter.setSeason(Season.AUTUMN));
		sidebar.add(autumnButton);
		sidebar.row();
		winterButton=UI.getSeasonButton(Season.WINTER, () -> seasonShifter.setSeason(Season.WINTER));
		sidebar.add(winterButton);
		sidebar.row();

		sidebar.setVisible(false);
		autumnButton.setVisible(false);
		winterButton.setVisible(false);

		Table sidebarPaddingTable = new Table();
		sidebarPaddingTable.padRight(RainbowSeedGame.WORLD_WIDTH * 0.0075f);
		sidebarPaddingTable.padTop(sidebarPaddingTable.getPadRight());
		sidebarPaddingTable.padBottom(sidebarPaddingTable.getPadRight());

		sidebarPaddingTable.setSize(RainbowSeedGame.UI_WIDTH_FRACTION * RainbowSeedGame.WORLD_WIDTH, RainbowSeedGame.WORLD_HEIGHT);
		sidebarPaddingTable.setPosition(RainbowSeedGame.WORLD_WIDTH - sidebarPaddingTable.getWidth()*0.75f, 0);
		sidebarPaddingTable.add(sidebar).grow().padRight(RainbowSeedGame.WORLD_WIDTH * 0.01f);
		addActor(sidebarPaddingTable);
	}

	public void introCutscene() {
		gameStage.getRoot().setTouchable(Touchable.disabled);
		addActor(new Intro(() -> {
			gameStage.getRoot().setTouchable(Touchable.enabled);
		}));
	}

	public void unlockSummerCutscene() {
		addAction(Actions.delay(1f, Actions.run(() -> {
			gameStage.getRoot().setTouchable(Touchable.disabled);
			addActor(new SummerUnlocked(() -> {
				gameStage.getRoot().setTouchable(Touchable.enabled);
				sidebar.setVisible(true);
			}));	
		})));
	}

	public void unlockWinterCutscene() {
		addAction(Actions.delay(1f, Actions.run(() -> {
			gameStage.getRoot().setTouchable(Touchable.disabled);
			addActor(new WinterUnlocked(() -> {
				gameStage.getRoot().setTouchable(Touchable.enabled);
				winterButton.setVisible(true);
			}));	
		})));
	}

	public void unlockAutumnCutscene() {
		addAction(Actions.delay(1f, Actions.run(() -> {
			gameStage.getRoot().setTouchable(Touchable.disabled);
			addActor(new AutumnUnlocked(() -> {
				gameStage.getRoot().setTouchable(Touchable.enabled);
				autumnButton.setVisible(true);
			}));
		})));
	}

	public void beginTheEnd() {
		sidebar.setVisible(true);
		addAction(Actions.delay(1f, Actions.run(() -> {
			gameStage.getRoot().setTouchable(Touchable.disabled);
			addActor(new BeginTheEnd(() -> {
				gameStage.getRoot().setTouchable(Touchable.enabled);
				showSummoningCircle();
			}, seasonShifter.getCurrentSeason()));
		})));
	}

	private void showSummoningCircle() {
		Runnable moveColorfulPlantsOutOfTheWay = () -> {
			sidebar.addAction(Actions.fadeOut(0.5f));
			List<DraggableItem> allFinalGuys = colorfulItems.get();
			float moveDuration = 0.75f;
			allFinalGuys.get(0).addAction(Actions.moveTo(350, 100, moveDuration));
			allFinalGuys.get(1).addAction(Actions.moveTo(100, 500, moveDuration));
			allFinalGuys.get(2).addAction(Actions.moveTo(250, 900, moveDuration));
			allFinalGuys.get(3).addAction(Actions.moveTo(1450, 890, moveDuration));
			allFinalGuys.get(4).addAction(Actions.moveTo(1700, 600, moveDuration));
			allFinalGuys.get(5).addAction(Actions.moveTo(1640, 410, moveDuration));
			allFinalGuys.get(6).addAction(Actions.moveTo(1500, 170, moveDuration));	
		};

		SummoningCircle circle = new SummoningCircle(() -> {
			addActor(new RainbowSeed());
			audio.rainbow();
			getRoot().setTouchable(Touchable.disabled);
			gameStage.getRoot().setTouchable(Touchable.disabled);
		}, moveColorfulPlantsOutOfTheWay);
		gameStage.addActor(circle);
		circle.toBack();
		seasonShifter.registerSeasonalThing(circle);
		colorfulItems.get().forEach(item -> item.addSummoningCircleDropTarget(circle));
	}

}
