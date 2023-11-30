package com.fidnortech.xjx.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaUtils {


    private static  final String SRC = "xjx";
    public static final String g = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZ/Ea8Oe6/tzZiriLeg5qYPqqrHXZFvD0+5wlGxVQ6HLzOCDj+GEQFoKK1k30fXE0KBQAZMIisdpmitigFCrbBQMO3CFDH2mauHCRYvkOJzLxUybciKlQqfxM+fe7Af+GnvPhKd6ZK32eJfBkHGu3TBrxSursGrPjcsfPK/H3UBQIDAQAB";

    public static final String s ="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJn8Rrw57r+3NmKuIt6Dmpg+qqsddkW8PT7nCUbFVDocvM4IOP4YRAWgorWTfR9cTQoFABkwiKx2maK2KAUKtsFAw7cIUMfaZq4cJFi+Q4nMvFTJtyIqVCp/Ez597sB/4ae8+Ep3pkrfZ4l8GQca7dMGvFK6uwas+Nyx88r8fdQFAgMBAAECgYEAgzfGc0mO2RJgJvErR6CwEYJ9XznI+p3xNvQcJJGtN6QbhBrfri+xb3Y2kTKw0hOOlrYyIkUIgqbCPzSon7tEhcfkPkdWkY4Hm2xcof1yI7j36uZAYznrZTAT/qW3pdZ3DCeDrswkrvKGBmzXlY8rVnNE9FV+zfeclLBuzleAyHUCQQD8Z775GKOBHY0nBHF6s/1wYFzwVtws6yBUZFTR85RvcXHO+1V3HQRrCmFM+7VLI5UQxCTjfVtKKcCBMO8tqqpHAkEAnC2yogC0Q4i4R00GtHL69j6DJT+S6Wq2XYXllrzdT7c6HLoSN2dolx1uXEHbPontJJh2n2q5GDwZ9BTEBmLpUwJBAMVbqavEZDSVEsWrZ7/19Plln8YdZNOKliDmwpvXxfzNy5t4Un9spQO9fg9f0YYLMIeHlwB6CGOY+fkKdjpS9AkCQEsQ++c6B2recBR1RV++VDm/WjGxGN/wVq8lCHEnXghOvTPJi6/tmCvqDX1yefrZOI56aam+FjE70g2rXXzkVdkCQQCTN3FNDTfd8U1lNQkhOqVeBcxnSmLbYJsaxnVkR7f6gOd/pnHRmwsw/R7deBWJNW0qqsdE4UNLrtb3A0BythZx";

//    public static void main(String[] args) throws Exception {
//        System.out.println("\n");
//        RsaKeyPair keyPair = generateKeyPair();
//        System.out.println("公钥：" + keyPair.getPublicKey());
//        System.out.println("私钥：" + keyPair.getPrivateKey());
//        System.out.println("\n");
//        test1(keyPair);
//        System.out.println("\n");
////        test2(keyPair);
//        System.out.println("\n");
//    }

    /**
     * 公钥加密私钥解密
     */
    private static void test1(RsaKeyPair keyPair) throws Exception {
        System.out.println("***************** 公钥加密私钥解密开始 *****************");
        String text1 = encryptByPublicKey(keyPair.getPublicKey(), RsaUtils.SRC);
        String text2 = decryptByPrivateKey(keyPair.getPrivateKey(), text1);
        System.out.println("加密前：" + RsaUtils.SRC);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (RsaUtils.SRC.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 公钥加密私钥解密结束 *****************");
    }

    /**
     * 私钥加密公钥解密
     * @throws Exception /
     */
    private static void test2(RsaKeyPair keyPair) throws Exception {
        System.out.println("***************** 私钥加密公钥解密开始 *****************");
        String text1 = encryptByPrivateKey(keyPair.getPrivateKey(), RsaUtils.SRC);
        String text2 = decryptByPublicKey(keyPair.getPublicKey(), text1);
        System.out.println("加密前：" + RsaUtils.SRC);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (RsaUtils.SRC.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 私钥加密公钥解密结束 *****************");
    }


    /**
     * 公钥解密
     *
     * @param publicKeyText 公钥
     * @param text 待解密的信息
     * @return /
     * @throws Exception /
     */
    public static String decryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 私钥加密
     *
     * @param privateKeyText 私钥
     * @param text 待加密的信息
     * @return /
     * @throws Exception /
     */
    public static String encryptByPrivateKey(String privateKeyText, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 私钥解密
     *
     * @param privateKeyText 私钥
     * @param text 待解密的文本
     * @return /
     * @throws Exception /
     */
    public static String decryptByPrivateKey(String privateKeyText, String text) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(Base64.decodeBase64(text));
            return new String(result);
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 公钥加密
     *
     * @param publicKeyText 公钥
     * @param text 待加密的文本
     * @return /
     */
    public static String encryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }
    /**
     * 构建RSA密钥对
     * @return /
     * @throws NoSuchAlgorithmException
     */
    public static RsaKeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        return new RsaKeyPair(publicKeyString, privateKeyString);
    }


    /**
     * RSA密钥对对象
     */
    public static class RsaKeyPair {

        private final String publicKey;
        private final String privateKey;

        public RsaKeyPair(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

    }
}
