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
    private ContractConfig contractConfig;

    @NestedConfigurationProperty
    private LoginConfig loginConfig;

    @NestedConfigurationProperty
    private FileConfig fileConfig;

    @NestedConfigurationProperty
    private JwtConfig jwtConfig;
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

    @Data
    public static class FileConfig {
        private String fileDir;
    }

    @Data
    public static class JwtConfig {
        private String jwtSecret;
    }

}
