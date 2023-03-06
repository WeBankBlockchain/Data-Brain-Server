package com.webank.databrain.utils;

import cn.hutool.core.codec.Base64;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;

public class AccountUtils {

    public static byte[] hash() {
        return null;
    }

    public static String encode(byte[] didBytes) {
        return Base64.encode(didBytes);
    }

    public static byte[] decode(String did) {
        return Base64.decode(did);
    }

    public static String getPwdHash(CryptoSuite cryptoSuite, String password, String salt) {
        return cryptoSuite.hash(password+":"+salt);
    }
}