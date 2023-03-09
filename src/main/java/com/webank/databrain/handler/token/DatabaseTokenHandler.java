package com.webank.databrain.handler.token;


import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.SessionInfoDAO;
import com.webank.databrain.model.po.SessionInfoDataObject;
import com.webank.databrain.handler.token.generator.ITokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseTokenHandler implements ITokenHandler{
    @Autowired
    private SysConfig sysConfig;
    @Autowired
    private ITokenGenerator tokenGenerator;

    @Autowired
    private SessionInfoDAO sessionInfoDAO;
    @Override
    public String generateToken(long accountPkId) {
        String token = tokenGenerator.generateToken();
        SessionInfoDataObject dataObject = new SessionInfoDataObject();
        dataObject.setToken(token);
        dataObject.setAccountId(accountPkId);
        dataObject.setExpiredAt(LocalDateTime.now().plusMinutes(sysConfig.getLoginConfig().getTokenExpireMinutes()));
        sessionInfoDAO.replace(dataObject);
        return token;
    }

    @Override
    public boolean verifyToken(String token) {
        //verify

        //delay expiration
        return true;
    }

}
