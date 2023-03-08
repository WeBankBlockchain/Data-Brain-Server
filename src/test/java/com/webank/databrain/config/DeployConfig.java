package com.webank.databrain.config;

import com.webank.databrain.blockchain.AccountModule;
import com.webank.databrain.blockchain.DataSchemaModule;
import com.webank.databrain.blockchain.ProductModule;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class DeployConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SysConfig sysConfig;
    @Autowired
    private CryptoKeyPair witnessKeyPair;

    @Autowired
    private Client client;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try{
            SysConfig.ContractConfig contractConfig = new SysConfig.ContractConfig();

            AccountModule accountModule = AccountModule.deploy(client, witnessKeyPair, witnessKeyPair.getAddress());
            contractConfig.setAccountContract(accountModule.getContractAddress());

            ProductModule productModule = ProductModule.deploy(client, witnessKeyPair, witnessKeyPair.getAddress(), accountModule.getContractAddress());
            contractConfig.setProductContract(productModule.getContractAddress());

            DataSchemaModule dataSchemaModule = DataSchemaModule.deploy(client, witnessKeyPair, witnessKeyPair.getAddress(), accountModule.getContractAddress());
            contractConfig.setDataSchemaContract(dataSchemaModule.getContractAddress());
            System.out.println("合约地址替换为临时部署的");
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }


    }
}
