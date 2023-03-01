package com.webank.databrain.utils;

import cn.hutool.core.util.HexUtil;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderService;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;

public class BlockchainUtils {

    public static void ensureTransactionSuccess(TransactionReceipt receipt) throws TransactionException {
        if (!receipt.isStatusOK()){
            throw new TransactionException(receipt.getTransactionHash() + "failed:"+receipt.getMessage());
        }
    }
}
