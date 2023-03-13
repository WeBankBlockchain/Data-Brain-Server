package com.webank.databrain.handler.token.impl;


import com.webank.databrain.config.SysConfig;
import com.webank.databrain.handler.token.ITokenHandler;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtTokenHandler implements ITokenHandler {
    @Autowired
    private SysConfig sysConfig;

    private Key signingKey ;

    @PostConstruct
    private void init(){
        signingKey = Keys.hmacShaKeyFor(sysConfig.getJwtConfig().getJwtSecret().getBytes());
    }

    @Override
    public String generateToken(String did) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + sysConfig.getLoginConfig().getTokenExpireMinutes()*60000); // 1 hour

        return Jwts.builder()
                .setSubject(did)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    @Override
    public String verifyToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKeyResolver(new SigningKeyResolverAdapter() {
                        @Override
                        public Key resolveSigningKey(JwsHeader header, Claims claims) {
                            return signingKey;
                        }
                    }).build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception ex) {
            // Handle signature exception
            return null;
        }
    }

}
