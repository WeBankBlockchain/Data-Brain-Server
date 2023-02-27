package com.webank.databrain.utils;

import cn.hutool.core.codec.Base64;
import com.webank.databrain.model.account.AccountID;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;

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
}