package com.webank.databrain.handler;

import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.handler.token.ITokenHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtTest extends ServerApplicationTests {

    @Autowired
    private ITokenHandler tokenHandler;

    @Test
    public void testGenerateAndVerify(){
        String did = "aaa";
        String token = tokenHandler.generateToken(did);
        System.out.println(token);

        String recovered = tokenHandler.verifyToken(token);
        Assertions.assertEquals(did, recovered);
    }
}
