package com.ctracker.server.model;

public class CTEvent {

	private Long 	eventId;
	private Long 	userId;
	private Float  	longitude;
	private Float  	latitude;
	private Float	altitude;
	private Long	datetime;

	public CTEvent() {}
	public CTEvent(Long userId, Float longitude, Float latitude, Float altitude, Long datetime) {
		super();
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.datetime = datetime;
	}

	public CTEvent(Long eventId, Long userId, Float longitude, Float latitude, Float altitude, Long datetime) {
		super();
		this.eventId = eventId;
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.datetime = datetime;
	}

	public CTEvent(Long userId, Float longitude, Float latitude, Float altitude) {
		super();
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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
