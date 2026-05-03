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
		setColor(new Color(Color.FOREST).mul(new Color(Color.GRAY)));
	}

	@Override
	public void summer() {
		setColor(new Color(Color.SALMON).mul(new Color(Color.LIGHT_GRAY)));
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
