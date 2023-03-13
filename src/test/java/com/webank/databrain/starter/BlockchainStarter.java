package com.webank.databrain.starter;

import com.webank.databrain.config.SysConfig;
import com.webank.databrain.contracts.AccountModule;
import com.webank.databrain.contracts.DataSchemaModule;
import com.webank.databrain.contracts.ProductModule;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlockchainStarter implements ApplicationListener<ContextRefreshedEvent> {

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
