package com.webank.databrain.handler.token;

public interface ITokenHandler {

    String generateToken();

    boolean verifyToken(String token);

}
