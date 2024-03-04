package com.coding.jwtspringsecure.service;


import com.coding.jwtspringsecure.dto.SignupRequest;
import com.coding.jwtspringsecure.dto.UserDto;

public interface AuthService {
    UserDto createdUser(SignupRequest signupRequest);
}
