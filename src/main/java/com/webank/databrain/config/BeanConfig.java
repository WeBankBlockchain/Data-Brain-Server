package com.webank.databrain.config;

import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Autowired
    private SysConfig sysConfig;

    @Bean
    public Client client(){
        BcosSDK bcosSDK = BcosSDK.build(sysConfig.getBcosCfg());
        return bcosSDK.getClient(sysConfig.getBcosGroupId());
    }

    @Bean
    public CryptoSuite cryptoSuite(){
        return new CryptoSuite(sysConfig.getCryptoConfig());
    }

}
