package com.example.project.controller;

import com.example.project.Model.AuthencationRequest;
import com.example.project.Model.RequestResponse;
import com.example.project.security.JwtUtil;
import com.example.project.service.UserAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.project.Model.ApplicationUser;
import com.example.project.service.ApplicationUserService;

import java.util.Optional;

@RestController
public class ApplicationUserController {

    Logger logger = LoggerFactory.getLogger(ApplicationUserController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userDetailsService;

    @Autowired
    private ApplicationUserService applicationUserService;

	@RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity register(@RequestBody ApplicationUser applicationUser){
        System.out.println(applicationUser);
        //check username and password in applicationuser if they match the policy return
//        if(applicationUser.user_email.contains("@") || applicationUser.password.isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password or username policy failed");
//        }
        logger.info("Application user is, ", applicationUser.toString());
        ApplicationUser user = applicationUserService.registerUser(applicationUser);
        return ResponseEntity.status(HttpStatus.OK).body("Registration Successful");
    }

    @RequestMapping(value="/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody AuthencationRequest authencationRequest) throws Exception {
//        return message Authentication Successful, and JWtoked, userid
        logger.info("In signin method");
        logger.info(authencationRequest.getUser_name()+ " " + authencationRequest.getPassword());
        authenticate(authencationRequest.getUser_name(),authencationRequest.getPassword());
        logger.info("not failes in authenticate");
        logger.info(authencationRequest.toString());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authencationRequest.getUser_name());
        logger.info(userDetails.getUsername() + " password is >" + userDetails.getPassword());
        final String token = jwtUtil.generateToken(userDetails);


        return ResponseEntity.ok(new RequestResponse(token));



    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
//    http://localhost:5000/viewprofile/user1
    @RequestMapping(value="/viewprofile/{userId}", method = RequestMethod.GET)
    public Optional<ApplicationUser> viewProfile(@PathVariable("userId") String id){
        logger.info("getting details for User = "+ id);
        return applicationUserService.getUser(id);
    }

    @RequestMapping(value="/editprofile/{userId}", method = RequestMethod.GET)
    public void editProfile(@PathVariable("userId") String id){
//        applicationUserService.updateUser(id);
    }

    @GetMapping("/hello")
    public String HelloWorld(){
        return "Hello World";
    }


    @Bean
    public AuthenticationManager AuthenticationManager(){
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }

}
