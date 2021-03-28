package com.vn.book_store_server.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vn.book_store_server.service.impl.UserDetailsImpl;
import com.vn.book_store_server.utils.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    public String genJwtToken(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + Constants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512((Constants.SECRET.getBytes())));
    }

    public String getUsernameFromJwtToken(String token){
        return JWT.decode(token).getSubject();
    }

    public Boolean validateJwtToken(String authToken){
        try{
            JWT.decode(authToken);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
