package com.mamay.inspection.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.log4j.Log4j2;
import org.apache.log4j.Logger;

@Log4j2
public class Encrypter {
	private static MessageDigest digester;

	static {
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.error(e);
		}
	}

	public static String crypt(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}

		digester.update(str.getBytes());
		byte[] hash = digester.digest();
		StringBuilder hexString = new StringBuilder();
		for (byte b : hash) {
			if ((0xff & b) < 0x10) {
				hexString.append("0").append(Integer.toHexString((0xFF & b)));
			} else {
				hexString.append(Integer.toHexString(0xFF & b));
			}
		}
		return hexString.toString();
	}
}
