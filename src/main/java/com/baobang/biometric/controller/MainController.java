package com.baobang.biometric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.biometric.entities.User;
import com.baobang.biometric.request_object.GetChallengeBody;
import com.baobang.biometric.request_object.LoginBody;
import com.baobang.biometric.request_object.RegisterBody;
import com.baobang.biometric.request_object.RequestBiometricAuthenBody;
import com.baobang.biometric.request_object.VertifyChallegeBody;
import com.baobang.biometric.results.DataResult;
import com.baobang.biometric.services.iml.UserService;
import com.baobang.biometric.utils.Utils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	UserService userSerivce;

	@RequestMapping(value = "/", method = RequestMethod.GET) //
	@ResponseBody
	@ApiOperation(value = "Hello Word!")
	public DataResult<String> helloWord() {
		return new DataResult<String>(HttpStatus.OK.value(), "Thành công.", "Hello word!");
	}

	@RequestMapping(value = "/register", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	@ApiOperation(value = "Tạo tài khoản")
	public DataResult<User> register(@RequestBody RegisterBody body) throws Exception {
		User user = null;
		if (userSerivce.checkUserNotBiometricAuthen(body.getUsername())) {
			user = userSerivce.saveUser(body);
		}
		return new DataResult<User>(HttpStatus.OK.value(), "Thành công.", user);
	}

	@RequestMapping(value = "/login", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	@ApiOperation(value = "Đăng nhập bằng tài khoản và mật khẩu")
	public DataResult<User> login(@RequestBody LoginBody body) throws Exception {
		User user = userSerivce.checkUserLogin(body);
		return new DataResult<User>(HttpStatus.OK.value(), "Thành công.", user);
	}

	@RequestMapping(value = "/get-challenge", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	@ApiOperation(value = "Yêu cầu đăng nhập bằng vân tay")
	public DataResult<User> getChallenge(@RequestBody GetChallengeBody body) throws Exception {
		User user = userSerivce.findByUsername(body.getUsername());
		if (user == null) {
			throw new Exception("Tài khoản chưa được đăng kí");
		}
		return new DataResult<User>(HttpStatus.OK.value(), "Thành công.", user);
	}

	@RequestMapping(value = "/verity-challengee", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	@ApiOperation(value = "Xác thực đăng nhập bằng vân tay")
	public DataResult<User> vertifyChallege(@RequestBody VertifyChallegeBody body) throws Exception {
		User user = userSerivce.findByUsername(body.getUsername());
		
		if (user == null) {
			throw new Exception("Đăng nhập thất bại");
		} else {
			boolean isVerify = Utils.verifyChallenge(user.getPublicKey(), user.getChallenge(), body.getChallenge());
			if (!isVerify) {
				throw new Exception("Đăng nhập thất bại");
			}
		}
		return new DataResult<User>(HttpStatus.OK.value(), "Thành công.", user);
	}

	@RequestMapping(value = "/request-biometric", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	@ApiOperation(value = "Yêu cầu bật đăng nhập bằng vân tay")
	public DataResult<Object> requestBiometricAuthen(@RequestBody RequestBiometricAuthenBody body) throws Exception {
		User user = userSerivce.findByUsername(body.getUsername());
		if (user == null) {
			throw new Exception("Không tìm thấy tài khoản");
		}
		user.setPublicKey(body.getPublicKey());
		user.setFlag(1);
		user.setChallenge(Utils.md5(user.getUsername() + user.getPassword()));
		userSerivce.updateUser(user);
		return new DataResult<Object>(HttpStatus.OK.value(), "Thành công.", null);
	}
}
