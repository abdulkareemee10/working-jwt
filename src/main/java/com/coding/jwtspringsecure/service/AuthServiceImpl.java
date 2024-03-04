package com.coding.jwtspringsecure.service;

import com.coding.jwtspringsecure.dto.SignupRequest;
import com.coding.jwtspringsecure.dto.UserDto;
import com.coding.jwtspringsecure.models.User;
import com.coding.jwtspringsecure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createdUser(SignupRequest signupRequest) {
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPhone(signupRequest.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));

        userRepository.save(user);

        User createdUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setEmail(createdUser.getEmail());
        userDto.setName(createdUser.getName());
        userDto.setPhone(createdUser.getPhone());
        userDto.setPassword(createdUser.getPassword());


        return userDto;
    }
}
