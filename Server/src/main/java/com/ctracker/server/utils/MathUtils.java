package com.ctracker.server.utils;


public class MathUtils {

	public static Double distance(CTLocation p1, CTLocation p2) {
		return Math.sqrt(Math.pow(p1.latitude - p2.latitude, 2)) 
				+ Math.sqrt(Math.pow(p1.latitude - p2.latitude, 2)) 
				+ Math.sqrt(Math.pow(p1.latitude - p2.latitude, 2));
	}
}
