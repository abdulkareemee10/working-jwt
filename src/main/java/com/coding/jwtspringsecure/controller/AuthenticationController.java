package com.coding.jwtspringsecure.controller;


import com.coding.jwtspringsecure.dto.AuthenticationRequest;
import com.coding.jwtspringsecure.dto.AuthenticationResponse;
import com.coding.jwtspringsecure.service.Jwt.UserDetailsServiceImpl;
import com.coding.jwtspringsecure.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
import java.io.IOException;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/authentication")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch(BadCredentialsException e){
            throw new BadCredentialsException("incorrect username or password");
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "user is not created, please register user first.");
            return null;
        }
                    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new AuthenticationResponse(jwt);

}
//    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest response) throws
//            BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
//        try{
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
//        }catch (BadCredentialsException e){
//
//            throw new BadCredentialsException("incorrect username or password.");
//
//        }catch(DisabledException disabledException){
//            return null;
//            }
//            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//
//            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//            return new AuthenticationResponse(jwt);
//        }

}
