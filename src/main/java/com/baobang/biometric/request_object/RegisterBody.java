package com.baobang.biometric.request_object;

import io.swagger.annotations.ApiModelProperty;

public class RegisterBody {
	@ApiModelProperty(notes = "Họ và tên người dùng", example = "Nguyễn Bảo Bằng")
	private String fullName;
	
	@ApiModelProperty(notes = "Tài khoản đăng nhập", example = "user")
	private String username;

	@ApiModelProperty(notes = "Mật khẩu đăng nhập", example = "123456")
	private String password;

	@ApiModelProperty(notes = "Khóa công khai", example = "hnksjdapso")
	private String publicKey;

	public RegisterBody() {
		super();
	}

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


	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	

}
