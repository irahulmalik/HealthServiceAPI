package com.example.project.service;

import com.example.project.Model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.simple.JSONObject;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.security.JwtUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Service
public class ApplicationUserService {

    Logger logger = LoggerFactory.getLogger(ApplicationUserService.class);

    @Autowired
    private ApplicationUserRepository repository;

    public ApplicationUser registerUser(ApplicationUser user){
        ApplicationUser applicationUser = repository.save(user);
        return applicationUser;
    }

    public Optional<ApplicationUser> getUser(String id) {
        logger.info("getting details of user with id:"+id);
        Optional<ApplicationUser> user = repository.findById(id);
        return user;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        ApplicationUser applicationUser = repository.findByuser_name(username);
//
//        if(applicationUser == null){
//            throw new UsernameNotFoundException("User with username: %s, not found", username);
//        }
//
//        Collection<SimpleGrantedAuthority> auhthorities = new ArrayList<>();
//
//        return new User(applicationUser.getUser_name(), applicationUser.getPassword(), authorities);
//    }
}

