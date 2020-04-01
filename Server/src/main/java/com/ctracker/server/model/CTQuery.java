package com.ctracker.server.model;


public class CTQuery {

	private Long userId;
	private Long maxContactDistance;
	private Long maxDateCount;
	private String accessToken;

	public CTQuery() {}
	public CTQuery(Long userId, Long maxContactDistance, Long maxDateCount, String accessToken) {
		super();
		this.userId = userId;
		this.maxContactDistance = maxContactDistance;
		this.maxDateCount = maxDateCount;
		this.accessToken= accessToken;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getMaxContactDistance() {
		return maxContactDistance;
	}
	public void setMaxContactDistance(Long maxContactDistance) {
		this.maxContactDistance = maxContactDistance;
	}
	public Long getMaxDateCount() {
		return maxDateCount;
	}
	public void setMaxDateCount(Long maxDateCount) {
		this.maxDateCount = maxDateCount;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
