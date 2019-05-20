package com.baobang.biometric.services;

import com.baobang.biometric.entities.User;

public interface IUserService {
	User findByUsername(String username);

	boolean checkUserNotBiometricAuthen(String username) throws Exception;

	boolean checkUserWithBiometricAuthen(String username) throws Exception;
	
}
