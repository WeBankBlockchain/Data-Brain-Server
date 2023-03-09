package com.webank.databrain.utils;

import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.exception.DataBrainException;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class HashUtils {
    public static byte[] sha256(byte[] input)  {
        try{
            // Get a MessageDigest instance for the SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Calculate the digest of the input byte array
            byte[] output = digest.digest(input);

            return output;
        }
        catch (NoSuchAlgorithmException ex){
            log.error("", ex);
            throw new DataBrainException(ErrorEnums.UnknownError);
        }

    }
}
