package com.baobang.biometric.request_object;

import io.swagger.annotations.ApiModelProperty;

public class RequestBiometricAuthenBody {
	
	@ApiModelProperty(notes = "Tên tài khoản", example = "user")
	private String username;
	
	@ApiModelProperty(notes = "Imei của thiết bị", example = "112312312")
	private String deviceImei;
	
	@ApiModelProperty(notes = "Khóa công khai", example = "hnksjdapso")
	private String publicKey;

	public String getDeviceImei() {
		return deviceImei;
	}

	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public RequestBiometricAuthenBody(String deviceImei, String publicKey) {
		super();
		this.deviceImei = deviceImei;
		this.publicKey = publicKey;
	}

	public RequestBiometricAuthenBody() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public RequestBiometricAuthenBody(String username, String deviceImei, String publicKey) {
		super();
		this.username = username;
		this.deviceImei = deviceImei;
		this.publicKey = publicKey;
	}
	
	
	
	
}
