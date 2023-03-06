package com.webank.databrain.utils;

public class SessionUtils {

    private static ThreadLocal<String> localDid = new ThreadLocal<>();

    public static String currentAccountDid() {
        return localDid.get();
    }

    public static void set(String did){
        localDid.set(did);
    }

}
