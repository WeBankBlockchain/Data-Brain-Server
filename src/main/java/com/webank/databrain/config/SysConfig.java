package com.webank.databrain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "system")
@Data
public class SysConfig {

    private String bcosCfg;

    private String bcosGroupId;

    private int cryptoConfig;

    private String salt;

    private ContractConfig contracts;

    @Data
    public static class ContractConfig {
        private String accountContract;
    }

}
