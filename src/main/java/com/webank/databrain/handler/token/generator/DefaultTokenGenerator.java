package com.webank.databrain.handler.token.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultTokenGenerator implements ITokenGenerator {

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
