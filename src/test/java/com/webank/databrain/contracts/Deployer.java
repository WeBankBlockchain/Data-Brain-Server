package com.webank.databrain.contracts;

import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;

//部署智能合约
public class Deployer {

    public static void main(String[] args) throws Exception{
        //Load config
        Client client = BcosSDK.build("config/config.toml").getClient();
        System.out.println(client);
        //Witness key
        String witnessPrivateKey = "11afa82f974469792aa0172931b813d4fc7dd9177f3211779efc5f955d5e480f";
        CryptoSuite cryptoSuite = new CryptoSuite(0);
        CryptoKeyPair witness = cryptoSuite.loadKeyPair(witnessPrivateKey);

        //Deploy Account Module
        AccountModule accountModule = AccountModule.deploy(client, witness, witness.getAddress());
        System.out.println("Account module address: "+accountModule.getContractAddress());

        //Deploy Product Module
        ProductModule productModule = ProductModule.deploy(client, witness, witness.getAddress(), accountModule.getContractAddress());
        System.out.println("Product module address: "+productModule.getContractAddress());

        //Deploy Data Schema Module
        DataSchemaModule dataSchemaModule = DataSchemaModule.deploy(client, witness, witness.getAddress(), accountModule.getContractAddress());
        System.out.println("Data Schema module address: "+dataSchemaModule.getContractAddress());
    }
}
