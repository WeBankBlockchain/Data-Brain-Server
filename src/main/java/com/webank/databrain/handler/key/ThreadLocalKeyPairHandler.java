package com.webank.databrain.handler.key;

import com.webank.databrain.config.SysConfig;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

//CryptoSuite不是线程安全的，多线程下会造成私钥获取错乱
@Component
public class ThreadLocalKeyPairHandler {

    @Autowired
    private SysConfig sysConfig;

    private ThreadLocal<CryptoSuite> cryptoSuite;

    @PostConstruct
    private void init(){
        this.cryptoSuite = ThreadLocal.withInitial(new Supplier<CryptoSuite>() {
            @Override
            public CryptoSuite get() {
                return new CryptoSuite(sysConfig.getCryptoConfig());
            }
        });
    }


    public CryptoSuite getCryptoSuite() {
        return cryptoSuite.get();
    }
}
