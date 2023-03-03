package com.webank.databrain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
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

    @NestedConfigurationProperty
    private ContractConfig contracts;

    @NestedConfigurationProperty
    private LoginConfig login;

    @Data
    public static class ContractConfig {
        private String accountContract;
        private String productContract;
        private String dataSchemaContract;
    }

    @Data
    public static class LoginConfig {
        private int tokenExpireMinutes;
    }

}
