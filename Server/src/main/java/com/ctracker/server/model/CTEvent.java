package com.ctracker.server.model;

public class CTEvent {

	private String 	userId;
	private Float  	longitude;
	private Float  	latitude;
	private Float	altitude;
	private Long	datetime;

	public CTEvent(String userId, Float longitude, Float latitude, Float altitude, Long datetime) {
		super();
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.datetime = datetime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getAltitude() {
		return altitude;
	}

	public void setAltitude(Float altitude) {
		this.altitude = altitude;
	}

	public Long getDatetime() {
		return datetime;
	}

	public void setDatetime(Long datetime) {
		this.datetime = datetime;
	}
}
