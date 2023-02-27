package com.webank.databrain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "system")
@Configuration
@Data
public class SysConfig {

    private String bcosCfg;

    private String bcosGroupId;

    private int cryptoConfig;

    private String salt;

    private String witnessPrivateKey;
    
    private ContractConfig contracts;

    @Data
    @ConfigurationProperties(prefix = "contract")
    public static class ContractConfig {
        private String accountContract;
    }

}
