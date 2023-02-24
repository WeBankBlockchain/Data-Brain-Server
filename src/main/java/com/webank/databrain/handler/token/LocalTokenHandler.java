package com.webank.databrain.handler.token;

import org.springframework.stereotype.Component;

@Component
public class LocalTokenHandler implements ITokenHandler{
    @Override
    public String generateToken() {
        return null;
    }

    @Override
    public String verifyToken() {
        return null;
    }
}
