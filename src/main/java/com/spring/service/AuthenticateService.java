package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.models.User;

@Service
public interface AuthenticateService {

    boolean IsValidUser(User user);

    String getJWT(User user);

    boolean IsValidRequest(String token);

    boolean registerUser(User user);

}
