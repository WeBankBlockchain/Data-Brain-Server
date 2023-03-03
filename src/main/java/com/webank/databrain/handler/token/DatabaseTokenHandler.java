package com.webank.databrain.handler.token;

import com.webank.databrain.db.dao.ITokenDAO;
import com.webank.databrain.handler.token.generator.ITokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTokenHandler implements ITokenHandler{

    @Autowired
    private ITokenGenerator tokenGenerator;

    @Autowired
    private ITokenDAO tokenDAO;
    @Override
    public String generateToken(String did) {
        String token = tokenGenerator.generateToken();
        tokenDAO.upsert(token,  did);
        return token;
    }

    @Override
    public boolean verifyToken(String token) {
        //verify

        //delay expiration
        return true;
    }

}
