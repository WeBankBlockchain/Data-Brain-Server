package com.webank.databrain.handler.token;

public interface ITokenHandler {

    String generateToken(String did);

    boolean verifyToken(String token);

}
