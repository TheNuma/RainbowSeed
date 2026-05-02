package com.numa.rainbow.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.numa.rainbow.season.Seasonal;

public class RainbowAudioManager implements Seasonal {

	private Music springSong;
	private Music summerSong;
	private Music autumnSong;
	private Music winterSong;

	private Music currentSong;
	private Music outgoingSong;

	private static final float VOLUME_CHANGE_SPEED = 0.75f;

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
		springSound.play();
	}

	@Override
	public void summer() {
		playSong(summerSong);
		summerSong.play();
	}

	@Override
	public void autumn() {
		playSong(autumnSong);
		autumnSound.play();
	}

	@Override
	public void winter() {
		playSong(winterSong);
		winterSound.play();
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

	public static Sound springSound;
	public static Sound summerSound;
	public static Sound autumnSound;
	public static Sound winterSound;

	public void initializeSounds() {
		springSound = Gdx.audio.newSound(Gdx.files.internal("sound/" + "springSound.wav"));
		summerSound = Gdx.audio.newSound(Gdx.files.internal("sound/" + "summerSound.wav"));
		autumnSound = Gdx.audio.newSound(Gdx.files.internal("sound/" + "autumnSound.wav"));
		winterSound = Gdx.audio.newSound(Gdx.files.internal("sound/" + "winterSound.wav"));
	}

}
