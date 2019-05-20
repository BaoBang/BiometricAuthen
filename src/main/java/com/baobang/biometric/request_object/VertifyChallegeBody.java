package com.baobang.biometric.request_object;

import io.swagger.annotations.ApiModelProperty;

public class VertifyChallegeBody {
	

	@ApiModelProperty(notes = "Tài khoản đăng nhập", example = "bangnb")
	private String username;
	
	@ApiModelProperty(notes = "Chuỗi được kí bằng private key", example = "hnksjdapso")
	private String challenge;

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}


	public VertifyChallegeBody() {
		super();
	}

	public VertifyChallegeBody(String username, String challenge) {
		super();
		this.username = username;
		this.challenge = challenge;
	}
	
	
	
	
}
