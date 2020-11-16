package com.ex.util;

import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES128 {

	public static String encrypt(String clearText, String secretKey, String initVector) {
        try {
            byte[] bytePass = secretKey.getBytes("utf-8");
            byte[] byteV = initVector.getBytes("utf-8");
            
            byte[] byteKey = Arrays.copyOf(bytePass, 16);
            byte[] byteIV = Arrays.copyOf(byteV, 16);
            // System.out.println(DatatypeConverter.printHexBinary(byteKey));
            // System.out.println(DatatypeConverter.printHexBinary(byteIV));
            
            SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(byteIV);
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
            
            byte[] byteText = clearText.getBytes("utf-8");
            byte[] buf = cipher.doFinal(byteText);
            
            byte[] byteBase64 = Base64.getEncoder().encode(buf);
            String data = new String(byteBase64);
            
            return data;
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
    }
    
    public static String decrypt(String data, String secretKey, String initVector) {
        try {
            byte[] byteData = Base64.getDecoder().decode(data.getBytes("utf-8"));
            byte[] bytePass = secretKey.getBytes("utf-8");
            byte[] byteV = initVector.getBytes("utf-8");
            
            byte[] byteKey = Arrays.copyOf(bytePass, 16);
            byte[] byteIV = Arrays.copyOf(byteV, 16);
            
            SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(byteIV);
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            
            byte[] byteText = cipher.doFinal(byteData);
            String clearText = new String(byteText);
            
            return clearText;
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
    }
    
}
