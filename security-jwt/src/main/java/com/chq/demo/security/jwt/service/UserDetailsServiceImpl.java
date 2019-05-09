package com.chq.demo.security.jwt.service;

import com.chq.demo.security.jwt.model.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        LoginUser user = new LoginUser();
        user.setUsername("admin");
        user.setPassword("$2a$10$.IgBTuviaFVZEZoveaOUGuvosjU4sL1R4TPFgwTUL3sBdaC8s9Y1C");
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        user.setRoles(roles);
        return user;
    }
}
