package com.webank.databrain.handler.token;

public interface ITokenHandler {

    String generateToken(long accountPkId);

    boolean verifyToken(String token);

}
