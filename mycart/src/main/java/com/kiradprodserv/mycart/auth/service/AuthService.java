package com.kiradprodserv.mycart.auth.service;

import com.kiradprodserv.mycart.auth.dto.AuthResponse;
import com.kiradprodserv.mycart.auth.dto.LogInRequest;
import com.kiradprodserv.mycart.auth.dto.RegisterRequest;
import com.kiradprodserv.mycart.auth.model.Role;
import com.kiradprodserv.mycart.auth.model.User;
import com.kiradprodserv.mycart.auth.repository.UserRepository;
import com.kiradprodserv.mycart.config.jwt.JwtService;
import com.kiradprodserv.mycart.exceptions.customExceptions.InvalidCredentialsException;
import com.kiradprodserv.mycart.exceptions.customExceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    public AuthResponse register(RegisterRequest registerRequest){
        if(userRepository.existsByEmail(registerRequest.email())){
            throw new UserAlreadyExistsException("Email already taken");
        }

        User user = new User();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRole(Role.USER);
        user.setUsername(registerRequest.username());

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthResponse(jwtToken,"SUCCESS","Everything is good");
    }

    public AuthResponse login(LogInRequest logInRequest){

        User user = userRepository.findByEmail(logInRequest.email()).orElseThrow(()->new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(logInRequest.password(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid Email or password");
        }
        String jwtToken = jwtService.generateToken(user);

        return new AuthResponse(jwtToken,"SUCCESS","Everything is good");

    }
}
