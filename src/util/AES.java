package util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {
    private static final String AES_ALGORITHM = "AES";
    private static final String PASSWORD = "2021217859"; // 密码，可以是任意长度

    public static void main(String[] args) throws Exception {
        // 生成AES密钥
        SecretKey secretKey = generateKey(PASSWORD);

        // 字符串加密和解密
        String plainText = "chenjunyang";
        String encryptedText = encrypt(plainText, secretKey);
        System.out.println("原字符串: " + plainText);
        System.out.println("加密后的字符串: " + encryptedText);
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("解密后的字符串: " + decryptedText);
    }

    // 生成AES密钥
    public static SecretKey generateKey(String password) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        secureRandom.setSeed(password.getBytes(StandardCharsets.UTF_8));
        keyGenerator.init(256, secureRandom); // AES密钥长度可以是128, 192, 或256
        return keyGenerator.generateKey();
    }

    // 加密字符串
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 解密字符串
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}