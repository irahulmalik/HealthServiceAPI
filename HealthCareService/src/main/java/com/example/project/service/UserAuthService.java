package com.example.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;



@Service
public class UserAuthService implements UserDetailsService{

    Logger logger = LoggerFactory.getLogger(UserAuthService.class);

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser1 = applicationUserRepository.findById(username);

        if(applicationUser1 == null){
            throw new UsernameNotFoundException("User with username: %s, not found");
        }
        ApplicationUser applicationUser = applicationUser1.get();

//        Collection<SimpleGrantedAuthority> auhthorities = new ArrayList<>();
//        auhthorities.addAll(GrantedAuthority);


//        return UserDetails(applicationUser.getUser_name(), applicationUser.getPassword(),auhthorities);
        return new User(applicationUser.getUser_name(), applicationUser.getPassword(), new ArrayList<>());
    }
}