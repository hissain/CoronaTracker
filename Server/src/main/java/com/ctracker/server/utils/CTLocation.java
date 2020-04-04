package com.ctracker.server.utils;

public class CTLocation {

	public CTLocation() {}
	
	public Double latitude;
	public Double longitude;
	public Double altitude;
	
	@Override
	public String toString() {
		return "lat: " + latitude + " lon: " + longitude + " alt: " + altitude;
	}
}
