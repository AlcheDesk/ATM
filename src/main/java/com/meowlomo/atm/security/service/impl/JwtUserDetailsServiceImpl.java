package com.meowlomo.atm.security.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.meowlomo.atm.security.jwt.JwtTokenProvider;
import com.meowlomo.atm.security.model.AuthenticatedUserDetails;

@Component
public class JwtUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Override
    public UserDetails loadUserByUsername(String userUuid) throws UsernameNotFoundException {
        if (userUuid != null) {
            AuthenticatedUserDetails jwtTokenUserDetails = new AuthenticatedUserDetails();
            try{
                UUID uuid = UUID.fromString(userUuid);
                //do something
                jwtTokenUserDetails.setUuid(uuid);
                return jwtTokenUserDetails;
            } catch (IllegalArgumentException exception){
                throw new UsernameNotFoundException("The user uuid [used as user name] is not valid "+userUuid);
            }
        }
        else {
            return null;
        }
    }
    
    public UserDetails loadUserByUserUuid(UUID userUuid) throws UsernameNotFoundException {
        if (userUuid != null) {
            AuthenticatedUserDetails jwtTokenUserDetails = new AuthenticatedUserDetails();
            try{
                //do something
                jwtTokenUserDetails.setUuid(userUuid);
                return jwtTokenUserDetails;
            } catch (IllegalArgumentException exception){
                throw new UsernameNotFoundException("The user uuid [used as user name] is not valid "+userUuid);
            }
        }
        else {
            return null;
        }
    }
    
    public UserDetails loadJwtUserDetailsByJwtTokenPayload(String jwtTokenPayload) throws UsernameNotFoundException {
        if (StringUtils.hasText(jwtTokenPayload)) {
            try{
                //do something
                AuthenticatedUserDetails userDetails = jwtTokenProvider.getJwtTokenUserDetails(jwtTokenPayload);
                return userDetails;
            } catch (IllegalArgumentException exception){
                throw new UsernameNotFoundException("The user token string is not valid "+jwtTokenPayload);
            }
        }
        else {
            return null;
        }
    }

}
