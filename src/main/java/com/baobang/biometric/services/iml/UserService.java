package com.baobang.biometric.services.iml;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobang.biometric.entities.User;
import com.baobang.biometric.request_object.LoginBody;
import com.baobang.biometric.request_object.RegisterBody;
import com.baobang.biometric.responsitories.UserResponsitory;
import com.baobang.biometric.services.IUserService;
import com.baobang.biometric.utils.Utils;

@Service
public class UserService implements IUserService {

	@Autowired
	UserResponsitory userResponsitory;

	@Override
	public User findByUsername(String username) {
		User user = userResponsitory.findByUsername(username);
		return user;
	}
	@Override
	public boolean checkUserNotBiometricAuthen(String username) throws Exception {
		User user = findByUsername(username);
		if (user != null) {
			throw new Exception("Tài khoản đã tồn tại");
		}
		return true;
	}

	@Override
	public boolean checkUserWithBiometricAuthen(String username) throws Exception {
		User user = findByUsername(username);
		if (user != null) {
			throw new Exception("Tài khoản đã tồn tại");
		}
		
		return true;
	}

	public User saveUser(RegisterBody body) throws NoSuchAlgorithmException {

		User user = new User();

		user.setUsername(body.getUsername());
		user.setPassword(body.getPassword());
		user.setFullName(body.getFullName());
		if (body.getPublicKey()!= null && !body.getPublicKey().isEmpty()) {
			user.setFlag(1);
			user.setPublicKey(body.getPublicKey());
			user.setChallenge(Utils.md5(body.getUsername() + body.getPassword()));
		}
		return userResponsitory.save(user);
	}

	public User checkUserLogin(LoginBody body) throws Exception {
		User user = userResponsitory.findByUsername(body.getUsername());
		if(user == null) {
			throw new Exception("Tài khoản không tồn tại");
		}
		user = userResponsitory.findByUsernameAndPassword(body.getUsername(), body.getPassword());
		if(user == null) {
			throw new Exception("Tài khoản hoặc mật khẩu không đúng");
		}
		return user;
	}

	public void updateUser(User user) {
		userResponsitory.save(user);
	}
}
