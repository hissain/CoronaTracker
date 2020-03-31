package com.ctracker.server.model;


public class CTUser {

	private Long userId;
	private String userNid;
	private String userPhoneNumber;
	private String userName;
	private String userDuid;

	public CTUser() {}
	public CTUser(String userNid, String userName, String userPhoneNumber, String userDuid) {
		super();
		this.userNid = userNid;
		this.userPhoneNumber = userPhoneNumber;
		this.userName = userName;
		this.userDuid = userDuid;
	}
	public CTUser(Long userId, String userNid, String userPhoneNumber, String userName, String userDuid) {
		super();
		this.userId = userId;
		this.userNid = userNid;
		this.userPhoneNumber = userPhoneNumber;
		this.userName = userName;
		this.userDuid = userDuid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNid() {
		return userNid;
	}

	public void setUserNid(String userNid) {
		this.userNid = userNid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDuid() {
		return userDuid;
	}

	public void setUserDuid(String userDuid) {
		this.userDuid = userDuid;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
}
