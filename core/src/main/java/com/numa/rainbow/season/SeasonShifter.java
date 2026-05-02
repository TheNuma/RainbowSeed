package com.numa.rainbow.season;

import java.util.ArrayList;
import java.util.Collection;

public class SeasonShifter {

	private Season currentSeason;
	private Collection<Seasonal> seasonChangeObservers;

	public SeasonShifter() {
		this.currentSeason = Season.SPRING;
		this.seasonChangeObservers = new ArrayList<>();
	}
	
	public void registerSeasonalThing(Seasonal seasonal) {
		seasonChangeObservers.add(seasonal);
	}

	public void setSeason(Season newSeason) {
		currentSeason = newSeason;
		
		seasonChangeObservers.forEach(observer -> {
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
			};
		});
	}

}
