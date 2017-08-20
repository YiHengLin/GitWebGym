package misc;

import java.security.SecureRandom;

public class GetSHA1 {
	
	public static String getSHA1(String password){
		String key = "temmujinfitness!"; // 對稱式金鑰
					  
//		byte[] iv = new byte[16]; // 初始向量
		byte[] iv = "0012254776511111".getBytes();
		SecureRandom srnd = new SecureRandom();
//		srnd.nextBytes(iv);
		String plainText = password;
		String cipherText = "";
		String decryptedString = "";
		String SHA1 = "";
		try {
			// encryptString(key, plainText, iv) : 將明文轉換為密文
			cipherText = CipherUtils.encryptString(key, plainText, iv);
			
			SHA1 = CipherUtils.getStringSHA1(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("註冊原始字串: " + plainText);
		System.out.println("註冊加密字串: " + cipherText);
		System.out.println("註冊SHA1: " + SHA1);
		return SHA1;
	}

}
