package com.webank.databrain.utils;

import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderInterface;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;

public class BlockchainUtils {

    public static void ensureTransactionSuccess(TransactionReceipt receipt, TransactionDecoderInterface txDecoder) throws TransactionException {

        if (!receipt.isStatusOK()){
            String msg = txDecoder.decodeRevertMessage(receipt.getOutput());
            throw new TransactionException(receipt.getTransactionHash() + " failed:"+msg);
        }
    }
}
