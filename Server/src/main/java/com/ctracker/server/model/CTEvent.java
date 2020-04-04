package com.ctracker.server.model;

public class CTEvent {

	private Long 	eventId;
	private Long 	userId;
	private Double  	longitude;
	private Double  	latitude;
	private Double	altitude;
	private Long	datetime;

	public CTEvent() {}
	public CTEvent(Long userId, Double longitude, Double latitude, Double altitude, Long datetime) {
		super();
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.datetime = datetime;
	}

	public CTEvent(Long eventId, Long userId, Double longitude, Double latitude, Double altitude, Long datetime) {
		super();
		this.eventId = eventId;
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.datetime = datetime;
	}

	public CTEvent(Long userId, Double longitude, Double latitude, Double altitude) {
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

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Long getDatetime() {
		return datetime;
	}

	public void setDatetime(Long datetime) {
		this.datetime = datetime;
	}
	
	@Override
	public String toString() {
		return "id: " + eventId + " user: " + userId + " lat: " + latitude + " lon: " + longitude + " alt: " + altitude;
	}
}
