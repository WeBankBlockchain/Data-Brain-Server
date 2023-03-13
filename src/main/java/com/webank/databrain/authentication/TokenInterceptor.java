package com.webank.databrain.authentication;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Order(1)
public class TokenInterceptor implements HandlerInterceptor {
//
//
//    @Autowired
//    private ITokenHandler tokenHandler;
//
//    @Autowired
//    private AccountInfoDAO accountInfoDAO;
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        try {
//            String token = extractTokenFromCookies(request.getCookies());
//            if (StringUtils.hasText(token)) {
//
//                String did = tokenHandler.verifyToken(token);
//                if (!StringUtils.hasText(did)){
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    response.getWriter().write("Invalid token");
//                    return false;
//                }
//
//                AccountInfoEntity accountInfoEntity = accountInfoDAO.selectByDid(did);
//                if (accountInfoEntity == null) {
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    response.getWriter().write("Invalid token");
//                    return false;
//                }
//                SecurityContextHolder.getContext().setAuthentication(new DidAuthentication(accountInfoEntity));
//            }
//        } catch (ExpiredJwtException ex) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.getWriter().write("Token has expired");
//            return false;
//        } catch (SignatureException ex) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.getWriter().write("Invalid token signature");
//            return false;
//        }
//        return true;
//    }
//
//    private String extractTokenFromCookies(Cookie[] cookies) {
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals(CommonConstants.JWT_COOKIE_NAME)) {
//                    return cookie.getValue();
//                }
//            }
//        }
//        return null;
//    }
}