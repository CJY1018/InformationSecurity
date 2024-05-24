import util.MyDES;
import util.MyRSA;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class Main {
    public static String TEXT = "abc";
    public static String DES_KEY = "2021217859";
    public static String TXT_SRC_File = "src/txt/src.txt";
    public static String TXT_ENC_File = "src/txt/enc.txt";
    public static String TXT_DEC_File = "src/txt/dec.txt";
    public static String IMG_SRC_File = "src/img/src.jpg";
    public static String IMG_ENC_File = "src/img/enc.jpg";
    public static String IMG_DEC_File = "src/img/dec.jpg";

    // DES 加密解密
    public static void DES() {
        System.out.println("原文：" + TEXT);

        // DES 加密
        long stime = System.currentTimeMillis();
        String encrypted_hex = MyDES.hex(MyDES.encryptECB(TEXT, DES_KEY));
        long etime = System.currentTimeMillis();
        String encrypted = MyDES.hex2byte(encrypted_hex);
        System.out.println();
        System.out.println("DES 加密结果:  " + encrypted);
        System.out.println("DES 加密耗时:  " + (etime - stime) + "ms");
        System.out.println();

        // DES 解密
        stime = System.currentTimeMillis();
        String decrypted_hex = MyDES.hex(MyDES.decryptECBOrigin(encrypted_hex, DES_KEY));
        etime = System.currentTimeMillis();
        String decrypted = MyDES.hex2byte(decrypted_hex);
        System.out.println("DES 解密结果:  " + decrypted);
        System.out.println("DES 解密耗时:  " + (etime - stime) + "ms");
    }

    // DES 文件加密解密
    public static void DESFile() {
        // 读取 src.txt 文件内容
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(TXT_SRC_File)));
            // 输出原文大小
            if (content.length() > 1024 * 1024) {
                System.out.println("src.txt 文件大小: " + String.format("%.2f", (float) content.length() / 1024 / 1024) + "MB");
            } else if (content.length() > 1024) {
                System.out.println("src.txt 文件大小: " + String.format("%.2f", (float) content.length() / 1024) + "KB");
            } else {
                System.out.println("src.txt 文件大小: " + content.length() + "B");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // DES 文件加密
        long stime = System.currentTimeMillis();
        String encrypted_hex = MyDES.hex(MyDES.encryptECB(content, DES_KEY));
        long etime = System.currentTimeMillis();
        String encrypted = MyDES.hex2byte(encrypted_hex);
        System.out.println();
        System.out.println("DES 文件加密耗时:  " + (etime - stime) + "ms");
        System.out.println();

        // 将加密后的字符串写入 enc.txt 文件
        try {
            Files.write(Paths.get(TXT_ENC_File), encrypted.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // DES 文件解密
        stime = System.currentTimeMillis();
        String decrypted_hex = MyDES.hex(MyDES.decryptECBOrigin(encrypted_hex, DES_KEY));
        etime = System.currentTimeMillis();
        String decrypted = MyDES.hex2byte(decrypted_hex);
        System.out.println("DES 文件解密耗时:  " + (etime - stime) + "ms");
        System.out.println();

        // 将解密后的字符串写入 dec.txt 文件
        try {
            Files.write(Paths.get(TXT_DEC_File), decrypted.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 比较 src.txt 和 dec.txt 文件内容是否一致
        try {
            String srcContent = Files.readString(Paths.get(TXT_SRC_File));
            String decContent = Files.readString(Paths.get(TXT_DEC_File));
            if (srcContent.equals(decContent)) {
                System.out.println("src.txt 和 dec.txt 文件内容一致，文件加密解密成功");
            } else {
                System.out.println("文件加密解密失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DES 图片加密解密
    public static void DESImage() {
        // 读取 src.jpg 图片文件内容
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = Files.readAllBytes(Paths.get(IMG_SRC_File));
            // 输出原图大小
            if (imageBytes.length > 1024 * 1024) {
                System.out.println("src.jpg 文件大小: " + String.format("%.2f", (float) imageBytes.length / 1024 / 1024) + "MB");
            } else if (imageBytes.length > 1024) {
                System.out.println("src.jpg 文件大小: " + String.format("%.2f", (float) imageBytes.length / 1024) + "KB");
            } else {
                System.out.println("src.jpg 文件大小: " + imageBytes.length + "B");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // DES 图片加密
        long stime = System.currentTimeMillis();
        String encrypted_hex = MyDES.hex(MyDES.encryptECB(Base64.getEncoder().encodeToString(imageBytes), DES_KEY));
        long etime = System.currentTimeMillis();
        System.out.println("DES 图片加密耗时:  " + (etime - stime) + "ms");
        System.out.println();

        // 将加密后的字节数组写入 enc.jpg 文件
        try {
            Files.write(Paths.get(IMG_ENC_File), encrypted_hex.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // DES 图片解密
        stime = System.currentTimeMillis();
        String decrypted_hex = MyDES.hex(MyDES.decryptECB(encrypted_hex, DES_KEY));
        etime = System.currentTimeMillis();
        System.out.println("DES 图片解密耗时:  " + (etime - stime) + "ms");
        System.out.println();

        // 将解密后的字节数组写入 dec.jpg 文件
        try {
            Files.write(Paths.get(IMG_DEC_File), Base64.getMimeDecoder().decode(MyDES.hex2byte(decrypted_hex)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 比较 src.jpg 和 dec.jpg 文件内容是否一致
        try {
            byte[] srcBytes = Files.readAllBytes(Paths.get(IMG_SRC_File));
            byte[] decBytes = Files.readAllBytes(Paths.get(IMG_DEC_File));
            if (Arrays.equals(srcBytes, decBytes)) {
                System.out.println("src.jpg 和 dec.jpg 文件内容一致，图片加密解密成功");
            } else {
                System.out.println("图片加密解密失败");
                System.out.println("src.jpg 文件大小: " + Files.readAllBytes(Paths.get(IMG_SRC_File)).length);
                System.out.println("dec.jpg 文件大小: " + Files.readAllBytes(Paths.get(IMG_DEC_File)).length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // RSA 加密解密
    public static void RSA() {
        // 生成RSA密钥对
        long stime = System.currentTimeMillis();
        MyRSA.generateKeyPair(1024);
        long etime = System.currentTimeMillis();
        System.out.println("RSA 公钥：" + MyRSA.getPublicKey());
        System.out.println("RSA 私钥：" + MyRSA.getPrivateKey());
        System.out.println("RSA 密钥生成耗时:  " + (etime - stime) + "ms");
        System.out.println();

        // RSA 加密
        System.out.println("原文：" + TEXT);
        System.out.println();
        stime = System.currentTimeMillis();
        String encrypted = MyRSA.encrypt(TEXT);
        etime = System.currentTimeMillis();
        System.out.println("RSA 加密结果:  " + encrypted);
        System.out.println("RSA 加密耗时:  " + (etime - stime) + "ms");
        System.out.println();

        // RSA 解密
        stime = System.currentTimeMillis();
        String decrypted = MyRSA.decrypt(encrypted);
        etime = System.currentTimeMillis();
        System.out.println("RSA 解密结果:  " + decrypted);
        System.out.println("RSA 解密耗时:  " + (etime - stime) + "ms");
    }

    public static void main(String[] args) {
        System.out.println("---------------------------DES---------------------------");
        DES();
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("---------------------------RSA---------------------------");
        RSA();
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("------------------------DES File-------------------------");
        DESFile();
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("------------------------DES Image-------------------------");
        DESImage();
        System.out.println("---------------------------------------------------------");
    }
}