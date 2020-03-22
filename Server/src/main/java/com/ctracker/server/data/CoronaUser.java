package com.ctracker.server.data;


public class CoronaUser {
	
	private Long userId;
	private String userNid;
	private String userName;
	private String userDuid;
	
	public CoronaUser() {}
	public CoronaUser(String userNid, String userName, String userDuid) {
		super();
		this.userNid = userNid;
		this.userName = userName;
		this.userDuid = userDuid;
	}
	public CoronaUser(Long userId, String userNid, String userName, String userDuid) {
		super();
		this.userId = userId;
		this.userNid = userNid;
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
}
