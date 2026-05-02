package com.numa.rainbow.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;
import com.numa.rainbow.season.Seasonal;

public class RainbowAudioManager implements Seasonal {

	private Music springSong;
	private Music summerSong;
	private Music autumnSong;
	private Music winterSong;

	private Music currentSong;
	private Music outgoingSong;

	private static final float VOLUME_CHANGE_SPEED = 0.5f;

	public void initializeMusic() {
		springSong = loadInSong("01 Spring");
		summerSong = loadInSong("02 Summer");
		autumnSong = loadInSong("03 Autumn");
		winterSong = loadInSong("04 Winter");

		springSong.play();
		summerSong.play();
		autumnSong.play();
		winterSong.play();

		playSong(springSong);
	}

	public void update() {
		float delta = Gdx.graphics.getDeltaTime();

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

	@Override
	public void spring() {
		playSong(springSong);
	}

	@Override
	public void summer() {
		playSong(summerSong);
	}

	@Override
	public void autumn() {
		playSong(autumnSong);
	}

	@Override
	public void winter() {
		playSong(winterSong);
	}

	public void playSong(Music newSong) {
		if (currentSong != newSong) {
			if (outgoingSong != null) {
				outgoingSong.setVolume(0);
				newSong.setPosition(currentSong.getPosition());
			}
			outgoingSong = currentSong;
			currentSong = newSong;
		}
	}

	private Music loadInSong(String name) {
		Music song = Gdx.audio.newMusic(Gdx.files.internal("music/" + name + ".wav"));
		song.setLooping(true);
		song.setVolume(0);
		song.play();
		return song;
	}

}
