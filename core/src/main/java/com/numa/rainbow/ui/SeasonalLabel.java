package com.numa.rainbow.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.numa.rainbow.season.Seasonal;

public class SeasonalLabel extends Label implements Seasonal {

	public SeasonalLabel(CharSequence text, LabelStyle style) {
		super(text, style);
	}

	@Override
	public void spring() {
		setColor(new Color(Color.FOREST).mul(new Color(new Color(0.65f, 0.65f, 0.65f, 1))));
	}

	@Override
	public void summer() {
		setColor(new Color(Color.LIME));
	}

	@Override
	public void autumn() {
		setColor(Color.ORANGE);
	}

	@Override
	public void winter() {
		setColor(UI.DARK_BLUE);
	}

}
