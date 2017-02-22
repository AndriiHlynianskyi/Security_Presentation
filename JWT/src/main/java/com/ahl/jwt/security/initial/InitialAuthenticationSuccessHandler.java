package com.ahl.jwt.security.initial;

import com.ahl.jwt.security.UserDetails;
import com.ahl.jwt.security.jwt.JwtTokenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class InitialAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenFactory jwtTokenFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails userContext = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtTokenFactory.createAccessToken(userContext);
        String refreshToken = jwtTokenFactory.createRefreshToken(userContext);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> tokenMap = new HashMap<String, String>();
        tokenMap.put("token", accessToken);
        tokenMap.put("refreshToken", refreshToken);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), tokenMap);

        clearAuthenticationAttributes(request);
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
