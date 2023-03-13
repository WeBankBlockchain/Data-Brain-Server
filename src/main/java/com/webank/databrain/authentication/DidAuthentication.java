package com.webank.databrain.authentication;

import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
public class DidAuthentication implements Authentication {

    private AccountInfoEntity currentAccount;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return currentAccount;
    }

    @Override
    public Object getPrincipal() {
        return currentAccount;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return currentAccount.getDid();
    }
}
