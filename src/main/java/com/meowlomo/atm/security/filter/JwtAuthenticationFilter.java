package com.meowlomo.atm.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.meowlomo.atm.security.exception.InvalidAuthnticationTokenException;
import com.meowlomo.atm.security.jwt.JwtTokenProvider;
import com.meowlomo.atm.security.service.impl.JwtUserDetailsServiceImpl;


/**
 * Filter for JWT token-based authentication.
 * <p>
 * Authentication tokens must be sent in the <code>Authorization</code> header and prefixed with <code>Bearer</code>:
 * <p>
 * <pre>
 *     Authorization: Bearer xxx.yyy.zzz
 * </pre>
 *
 * @author scott.fu
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    
    @Value("${meowlomo.secuirty.authentication-enabled:true}")
    private boolean authenticationEnabled;
    
    @Value("${meowlomo.security.authentication.header.name:Authorization}")
    private String authenticationHeaderName;
    
    @Value("${meowlomo.security.jwt.header.prefix:'Bearer '}")
    private String jwtTokenPrefix;
    
    @Value("${meowlomo.security.cookie.name:meowlomo_token}")
    private String cookieName;
    
    @Value("${meowlomo.testing.security.username:tester}")
    private String testingUserName;
    
    @Value("${meowlomo.testing.security.user-uuid:@null}")
    private String testingUserUUIDString;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = this.getJwtFromRequestHeader(request);
        logger.debug("JWT filter caught jwt token {}", jwt);
        //start to check the token content
        if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {          
            UserDetails userDetails = jwtUserDetailsService.loadJwtUserDetailsByJwtTokenPayload(jwt);
            if (userDetails == null) {
                logger.error("user detail from jwt token is null. jwt is {}", jwt);
            }
            else {
                logger.debug("JWT filter created user details class name {}", userDetails.getClass().getName());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // set the user details to the token
                usernamePasswordAuthenticationToken.setDetails(userDetails);
                // set the security context
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                logger.debug("JWT filter insert authntication class name {}", usernamePasswordAuthenticationToken.getClass().getName());
            }
        }
        else {
           // if we need to throw error for the token
          if (authenticationEnabled) {
              logger.warn("The http call is missing the jwt token.");
              throw new InvalidAuthnticationTokenException("The authnetication token is empty or non exsit.");
          }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * get the JWT token string form the http request
     * @param request
     * @return String the jwt
     */
    private String getJwtFromRequestHeader(HttpServletRequest request) {
        //check the token from header
        String tokenString = request.getHeader(authenticationHeaderName);
        logger.debug("JWT filter caught request header Authorization value {}", tokenString);
        if (StringUtils.hasText(tokenString) && tokenString.startsWith(jwtTokenPrefix)) {
            return tokenString.substring(jwtTokenPrefix.length(), tokenString.length());
        }
        
        Cookie cookieToken = WebUtils.getCookie(request, cookieName);
        if (cookieToken != null) {
            return cookieToken.getValue();
        }
        return null;
    }
}