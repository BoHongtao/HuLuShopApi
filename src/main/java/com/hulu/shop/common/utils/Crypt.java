package com.hulu.shop.common.utils;


public final class Crypt {

    static String key = "boht666";

    public static String xxteaEncode(String str, String key) {
        String encrypt_data = XXTEA.encryptToBase64String(str, key);
        return encrypt_data;
    }

    public static String xxteaDecode(String str, String key) {
        String decrypt_data = XXTEA.decryptBase64StringToString(str, key);
        return decrypt_data;
    }

    public static String xxteaEncode(String str) {
        String encrypt_data = XXTEA.encryptToBase64String(str, key);
        return encrypt_data;
    }

    public static String xxteaDecode(String str) {
        String decrypt_data = XXTEA.decryptBase64StringToString(str, key);
        return decrypt_data;
    }
}
