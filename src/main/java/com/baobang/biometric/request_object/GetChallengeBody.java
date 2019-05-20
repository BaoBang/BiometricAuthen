package com.baobang.biometric.request_object;

import io.swagger.annotations.ApiModelProperty;

public class GetChallengeBody {
	@ApiModelProperty(notes = "Tên tài khoản đăng nhập", example = "bangnb")
	private String username;

	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public GetChallengeBody() {
		super();
	}
	
	
}
