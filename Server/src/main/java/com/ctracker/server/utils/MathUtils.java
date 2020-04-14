package com.ctracker.server.utils;


public class MathUtils {


	public static double distance(CTLocation p1, CTLocation p2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(p2.latitude - p1.latitude);
	    double lonDistance = Math.toRadians(p2.longitude - p1.longitude);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(p1.latitude)) * Math.cos(Math.toRadians(p2.latitude))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = p1.altitude - p2.altitude;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}
}


