package util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class MyRSA {
    private static BigInteger n; // 模数
    private static BigInteger e; // 公钥
    private static BigInteger d; // 私钥

    // 生成一个k位的RSA密钥对
    public static void generateKeyPair(int k) {
        SecureRandom rand = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(k / 2, rand);
        BigInteger q = BigInteger.probablePrime(k / 2, rand);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(k / 4, rand);
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    // 加密消息
    public static String encrypt(String text) {
        BigInteger message = new BigInteger(text.getBytes());
        return message.modPow(e, n).toString();
    }

    // 解密消息
    public static String decrypt(String encrypted) {
        BigInteger message = new BigInteger(encrypted);
        return new String(message.modPow(d, n).toByteArray());
    }

    // 获取公钥
    public static BigInteger getPublicKey() {
        return e;
    }

    // 获取私钥
    public static BigInteger getPrivateKey() {
        return d;
    }

    // 获取模数
    public static BigInteger getModulus() {
        return n;
    }
}