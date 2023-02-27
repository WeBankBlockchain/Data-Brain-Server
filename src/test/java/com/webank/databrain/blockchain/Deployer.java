package com.webank.databrain.blockchain;

import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;

public class Deployer {

    public static void main(String[] args){
        CryptoSuite cryptoSuite = new CryptoSuite(0);
        System.out.println(cryptoSuite.getCryptoKeyPair().getHexPrivateKey());
    }
}
