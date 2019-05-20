package com.baobang.biometric.request_object;

import io.swagger.annotations.ApiModelProperty;

public class LoginBody {
	@ApiModelProperty(notes = "Tài khoản đăng nhập", example = "user")
	private String username;

	@ApiModelProperty(notes = "Mật khẩu đăng nhập", example = "123456")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginBody(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public LoginBody() {
		super();
	}

	
	
}
