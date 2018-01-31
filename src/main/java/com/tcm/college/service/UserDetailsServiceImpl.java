package com.tcm.college.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tcm.college.entity.Consumer;
import com.tcm.college.exception.CollegeException;
import com.tcm.college.utill.Constant;

@Component
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ConsumerService consumerService;

    @Override
    public UserDetails loadUserByUsername(String email)
    {
        try
        {
            logger.info(email + " - Try to log-in.");
            Consumer consumer = consumerService.getConsumer(email);

            if (consumer == null)
            {
                throw new UsernameNotFoundException("User is not found.");
            }

            logger.info(email + " - user details : " + consumer);
            
            boolean enabled = true;

            boolean accountNonExpired = true;

            boolean credentialsNonExpired = true;

            boolean accountNonLocked = true;
            if (Constant.CONSUMER_INACTIVE == consumer.getStatus())
            {
                accountNonLocked = false;
            }
            
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(consumer.getConsumerRole().getRoleName());
            
            return new User(consumer.getEmail(), consumer.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorityList);

        }
        catch (CollegeException collegeException)
        {
            logger.error(email + " - Error in log-in. " + collegeException.getMessage(), collegeException);
        }
        return null;
    }
   
}
