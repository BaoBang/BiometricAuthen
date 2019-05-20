package com.baobang.biometric.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Utils {

	public static String md5(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	public static boolean verifyChallenge(String publicKey, String challenge, String challengeResponse) {
		try {
			PublicKey key = loadPublicKey(publicKey);
			if (key != null) {
				Signature verificationFunction = Signature.getInstance("SHA256withECDSA");
				verificationFunction.initVerify(key);
				verificationFunction.update(challenge.getBytes());
				byte[] responseByte = Base64.getDecoder().decode(challengeResponse.getBytes());
				return verificationFunction.verify(responseByte);
			}
		} catch (Exception e) {
			
		}
		return false;
	}

	private static PublicKey loadPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] publicKeyByte = Base64.getDecoder().decode(publicKey);
		if (publicKeyByte != null) {
			KeyFactory keyFactory = KeyFactory.getInstance("EC");
			return keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyByte));
		}
		return null;
	}
}
