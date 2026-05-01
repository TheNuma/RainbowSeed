package com.numa.rainbow.audio;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

public class RainbowAudioManager {

	public static Music winterSong;
	public static Music springSong;

	private static Music currentSong;
	private static Music outgoingSong;

	private static final float VOLUME_CHANGE_SPEED = 5f;

	private static float timer;

	public static void initializeMusic() {
		winterSong = Gdx.audio.newMusic(Gdx.files.internal("music/01 Winter.wav"));
		winterSong.setLooping(true);
		winterSong.setVolume(0);
		springSong = Gdx.audio.newMusic(Gdx.files.internal("music/02 Spring.wav"));
		springSong.setLooping(true);
		springSong.setVolume(0);

		winterSong.play();
		springSong.play();

	}

	public static void update() {
		float delta = Gdx.graphics.getDeltaTime();

		// Goofy code block for testing vertical remixing
		timer -= delta;
		if (timer <= 0f) {
			timer = new Random().nextFloat() * 4f + 8f;
			if (currentSong == winterSong) {
				playSong(springSong);
			} else if (currentSong == springSong) {
				playSong(winterSong);
			}

		}
		// End

		if (outgoingSong != null) {
			float currentVolume = outgoingSong.getVolume();
			float newVolume = currentVolume - delta * VOLUME_CHANGE_SPEED;
			newVolume = MathUtils.clamp(newVolume, 0f, 1f);
			outgoingSong.setVolume(newVolume);
		}

		if (currentSong != null) {
			float currentVolume = currentSong.getVolume();
			float newVolume = currentVolume + delta * VOLUME_CHANGE_SPEED;
			newVolume = MathUtils.clamp(newVolume, 0f, 1f);
			currentSong.setVolume(newVolume);
		}

	}

	public static void playSong(Music newSong) {
		if (currentSong != newSong) {
			if (outgoingSong != null) {
				outgoingSong.setVolume(0);
			}
			outgoingSong = currentSong;
			currentSong = newSong;
		}
	}

}
