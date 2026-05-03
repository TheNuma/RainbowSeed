package com.numa.rainbow.season;

import java.util.ArrayList;
import java.util.Collection;

public class SeasonShifter {

	private Season currentSeason;
	private final Collection<Seasonal> seasonChangeObservers;

	public SeasonShifter() {
		currentSeason = Season.SPRING;
		seasonChangeObservers = new ArrayList<>();
	}

	public void registerSeasonalThing(Seasonal seasonal) {
		seasonChangeObservers.add(seasonal);
		notifySeasonalObserver(seasonal);
	}

	public void setSeason(Season newSeason) {
		if (newSeason != currentSeason) {
			currentSeason = newSeason;
			seasonChangeObservers.forEach(this::notifySeasonalObserver);
		}
	}

	private void notifySeasonalObserver(Seasonal observer) {
		switch (currentSeason) {
		case AUTUMN:
			observer.autumn();
			break;
		case SPRING:
			observer.spring();
			break;
		case SUMMER:
			observer.summer();
			break;
		case WINTER:
			observer.winter();
			break;
		}
	}

	public Season getCurrentSeason() {
		return currentSeason;
	}

}
