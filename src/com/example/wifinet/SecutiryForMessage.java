package com.example.wifinet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecutiryForMessage {
	public final static String MD5ToEncrypt(String Message){
		byte[] mebyte = Message.getBytes();
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(mebyte);
			byte[] afterEMessage = mdInst.digest();
			int j = afterEMessage.length;
            char str[] = new char[j * 2];
            int k = 0;
            char hexDigits[]={'0','1','2','3','4','5','6','7',
            		'8','9','A','B','C','D','E','F'};
            for (int i = 0; i < j; i++) {
                byte byte0 = afterEMessage[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
