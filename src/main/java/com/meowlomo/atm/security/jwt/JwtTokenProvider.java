package com.meowlomo.atm.security.jwt;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.meowlomo.atm.config.multitenancy.TentantContext;
import com.meowlomo.atm.security.model.AuthenticatedUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${meowlomo.security.jwt.key}")
    private String jwtSecret;

    @Value("${meowlomo.security.jwt.expiration-in-minutes:30}")
    private int jwtExpirationInMinutes;
    
    @Autowired
    private ApplicationContext appContext;

    public String generateToken(Authentication authentication) {

        AuthenticatedUserDetails jwtTokenUserDetails = (AuthenticatedUserDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMinutes*60*1000);

        String userUUID = jwtTokenUserDetails.getUuid().toString();
        logger.info("Generate JWT for user with UUID "+ userUUID);
        
        return Jwts.builder()
                .setSubject(userUUID)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .setIssuer("meowlomo")
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.RS512, jwtSecret)
                .compact();
    }

    public String getUserUUIDFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            logger.debug("jwt using key {}",jwtSecret);
            String encodedJwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
            Jwts.parser().setSigningKey(encodedJwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature {}",ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token {}",ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token {}",ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token {}",ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty. {}",ex.getMessage());
        }
        return false;
    }
    
    public String generateTokenByUUID(String uuid) {
        logger.info("Generate JWT for user with UUID "+ uuid);
        
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMinutes*60*1000);
     
        return Jwts.builder()
                .setSubject(uuid)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .setIssuer("meowlomo")
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    
    public AuthenticatedUserDetails getJwtTokenUserDetails(String jwtToken) {
        //we use the standard object mapper to perform the convention. 
        ObjectMapper mapper = new ObjectMapper();
        try {
            String encodedJwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
            Claims claims = Jwts.parser()
                    .setSigningKey(encodedJwtSecret)
                    .parseClaimsJws(jwtToken)
                    .getBody();
            String jwtPayloadSub = claims.getSubject();
            if(JsonParser.parseString(jwtPayloadSub) == null) {
                logger.error("missing jwt token from request call.");
                return null;
            }
            
            JsonObject jsonObject = JsonParser.parseString(jwtPayloadSub).getAsJsonObject();   
            
            if (jsonObject.get("tenantId") == null) {
                logger.error("jwt token is missing tenatn id.");
                return null;
            }

         
            Long currentTentantId = jsonObject.get("tenantId").getAsLong();
            TentantContext tenantContext = new TentantContext(appContext);
            tenantContext.setCurrentTentatantId(currentTentantId);
            
            tenantContext.setCurrentTentatantId(currentTentantId);
            return mapper.readValue(jwtPayloadSub, AuthenticatedUserDetails.class);
        } catch (IOException e) {
            logger.warn("Error on converting JWT token.", e);
        }
        return null;
    }
}