package com.numa.rainbow.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class PossibleComboHint extends Group {

	public PossibleComboHint(Actor parent) {
		Texture sparkleTexture = new Texture(Gdx.files.internal("ui/sparkle.png"));

		Image sparkle = new Image(sparkleTexture);
		sparkle.setTouchable(Touchable.disabled);
		sparkle.setOrigin(Align.center);
		sparkle.setPosition(parent.getX() + parent.getWidth()/2f, parent.getY() + parent.getHeight()/2f, Align.center);
		float finalScale = MathUtils.random(0.5f, 1.0f);
		sparkle.setScale(0);
		sparkle.setColor(new Color(Color.WHITE).lerp(new Color(Color.ORANGE), MathUtils.random(1f)));
		float directionDegrees = MathUtils.random(360f);
		float moveDistanceX = MathUtils.random(20, 70);
		float moveDistanceY = MathUtils.random(20, 70);
		sparkle.addAction(
				Actions.delay(
						0f,
						Actions.parallel(
								Actions.moveBy(MathUtils.sinDeg(directionDegrees) * moveDistanceX, MathUtils.cosDeg(directionDegrees) * moveDistanceY, 1.25f, Interpolation.exp10Out),
								Actions.sequence(
										Actions.scaleTo(finalScale, finalScale, MathUtils.random(0.3f, 0.7f), Interpolation.elasticOut),
										Actions.scaleTo(0, 0, MathUtils.random(0.3f, 0.7f), Interpolation.pow2),
										Actions.removeActor()
										)
								)
						)
				);
		sparkle.rotateBy(5);
		float spinDuration = MathUtils.random(0.25f, 1f);
		int startDirection = MathUtils.randomBoolean() ? 1 : -1;
		sparkle.addAction(
				Actions.forever(
						Actions.sequence(
								Actions.rotateBy(startDirection * -10f, spinDuration, Interpolation.fade),
								Actions.rotateBy(startDirection * 10f, spinDuration, Interpolation.fade)
								)
						)
				);
		addActor(sparkle);
	}



	@Override
	public boolean remove() {
		getChildren().forEach(child -> getStage().addActor(child));
		return super.remove();
	}

}
