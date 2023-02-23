package com.webank.databrain.utils;

import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;

public class BlockchainUtils {

    public static void ensureTransactionSuccess(TransactionReceipt receipt) throws TransactionException {
        if (!receipt.isStatusOK()){
            throw new TransactionException(receipt.getTransactionHash() + "failed");
        }
    }
}
