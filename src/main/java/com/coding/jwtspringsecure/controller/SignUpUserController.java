package com.coding.jwtspringsecure.controller;


import com.coding.jwtspringsecure.dto.SignupRequest;
import com.coding.jwtspringsecure.dto.UserDto;
import com.coding.jwtspringsecure.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpUserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest){
        UserDto createdUser  =  authService.createdUser(signupRequest);

        if (createdUser == null)
            return new ResponseEntity("user is not created", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
