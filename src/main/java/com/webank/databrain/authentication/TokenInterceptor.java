package com.webank.databrain.authentication;

import com.webank.databrain.constants.CommonConstants;
import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import com.webank.databrain.dao.db.dao.AccountInfoDAO;
import com.webank.databrain.handler.token.ITokenHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class TokenInterceptor implements HandlerInterceptor {


    @Autowired
    private ITokenHandler tokenHandler;

    @Autowired
    private AccountInfoDAO accountInfoDAO;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = extractTokenFromCookies(request.getCookies());
            if (StringUtils.hasText(token)) {

                String did = tokenHandler.verifyToken(token);
                if (!StringUtils.hasText(did)){
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write("Invalid token");
                    return false;
                }

                AccountInfoEntity accountInfoEntity = accountInfoDAO.selectByDid(did);
                if (accountInfoEntity == null) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write("Invalid token");
                    return false;
                }
                SecurityContextHolder.getContext().setAuthentication(new DidAuthentication(accountInfoEntity));
            }
        } catch (ExpiredJwtException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token has expired");
            return false;
        } catch (SignatureException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid token signature");
            return false;
        }
        return true;
    }

    private String extractTokenFromCookies(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CommonConstants.JWT_COOKIE_NAME)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}