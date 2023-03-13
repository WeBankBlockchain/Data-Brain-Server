package com.webank.databrain.utils;

import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUtils {


    public static String currentAccountDid() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static AccountInfoEntity currentAccount() {
        return (AccountInfoEntity) SecurityContextHolder.getContext().getAuthentication();
    }
}
