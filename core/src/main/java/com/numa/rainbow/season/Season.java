package com.numa.rainbow.season;

import java.util.Set;

public enum Season {
	SPRING, SUMMER, AUTUMN, WINTER;
	
	public static final Set<Season>springOnly=Set.of(SPRING);
	public static final Set<Season>summerOnly=Set.of(SUMMER);
	public static final Set<Season>autumnOnly=Set.of(AUTUMN);
	public static final Set<Season>winterOnly=Set.of(WINTER);
	public static final Set<Season>allSeasons=Set.of(SPRING,SUMMER,AUTUMN,WINTER);
	public static final Set<Season>notWinter=Set.of(SPRING,SUMMER,AUTUMN);
}
