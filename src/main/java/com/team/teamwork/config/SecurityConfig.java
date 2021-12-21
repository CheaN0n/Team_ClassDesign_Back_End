package com.team.teamwork.config;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SecurityConfig {
    //在网站生成：http://web.chacuo.net/netrsakeypair
    private static final String privateKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKhPhqrgQwMT9TAXjFoRvJNj9xKGKb4SXHamkyEA7T0IdNX2tYhu2h51dZp4wtKUd+DEXk78dh+u+xFbr+iNVS0Qz5jAG7UtmUoHxL2WQlf1J+6xgwyuHUkIndfxsKIW8dScVGG3G2BRn+2SL0CTBuqAVB11Kp4c+Bym1TQQ21GbAgMBAAECgYBDvk6LtFwEfyHuy2uYTQ9Dm7a0Y/+lGyrQLteFLyRNrnuoKvaCBWwRWmGNXRG9RDjD5QW4cPDya7FuGSNCTLB9HevKC7HT60VJfvyROwM5SXmdde2T+uzHZZLLEnwcIg3m4aC+rKshV64XemtadZQ4/mJsXCiXy+M8/IvviaJisQJBANKe4FBUA/TcqwVLYRkbT6keVDoCgc+Hin3VIGWAxjE76eMf4Hq8MV7m7FKwj4ChLXTU/qSShyeKLZY0qob8UJ8CQQDMkvmLDW/Ck2L4tc1h1okna9kqeQMhMW4BknWeUu1TdH7no0GJm0bpaUuzRKWcYJbXBGv72Zlo0AEyMpj5STGFAkEAz8rhAT8yPBStQWbx38Q7Hl2GuUiZ6zYM40IuWRXn8tDqWiKr5Erg5oEq7BW3Li9V2mr84z6QyuifOw6wosYxfwJBAKjeAeOIueJx61bGK71BbIIAAomOzsiNlvLxROnmJkWnekIXfzfp10VBR925IsPM73aaDdEdNAdS/EnFfoT4qEkCQQCAd7IKiLP8AyTHv+MsvJ3F+OQG6sFuXz+KUz+VU1OPvpUMeXvlILO0svI8JJxpnH+nhpK84j4VlCgY2MDw9cq3";
    private static final String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoT4aq4EMDE/UwF4xaEbyTY/cShim+Elx2ppMhAO09CHTV9rWIbtoedXWaeMLSlHfgxF5O/HYfrvsRW6/ojVUtEM+YwBu1LZlKB8S9lkJX9SfusYMMrh1JCJ3X8bCiFvHUnFRhtxtgUZ/tki9AkwbqgFQddSqeHPgcptU0ENtRmwIDAQAB";

    /**
     * RSA公钥加密
     * @param str 加密字符串
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt( String str) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     * @param str 加密字符串
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

}
