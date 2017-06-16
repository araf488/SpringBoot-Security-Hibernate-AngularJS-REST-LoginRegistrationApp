package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.spring.constants.Constant;
import com.spring.models.User;
import com.spring.repository.AuthenticateServiceRepository;
import com.spring.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    AuthenticateServiceRepository authenticateServiceRepo;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean IsValidUser(User user) {
        User resultUser = authenticateServiceRepo.findByEmail(user.getEmail());
        if (resultUser != null && BCrypt.checkpw(user.getPassword(), resultUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public String getJWT(User user) {
        String token = Jwts.builder().setSubject("AngularWithSpringMVC").setId(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + 6000000L)).signWith(SignatureAlgorithm.HS512, Constant.KEY)
                .compact();
        return token;
    }

    public boolean IsValidRequest(String token) {
        System.out.println(Jwts.parser().setSigningKey(Constant.KEY).parseClaimsJws(token).getBody().get("jti"));
        return Jwts.parser().setSigningKey(Constant.KEY).parseClaimsJws(token).getBody().getSubject()
                .equals("AngularWithSpringMVC");
    }

    public String getUser(HttpServletRequest request) {
        return null;

    }

    @Override
    public boolean registerUser(User user) {

        String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        user.setRole("NU");
        User retUser = userRepository.save(user);

        if (retUser != null) {
            return true;
        } else {
            return false;
        }
    }
}
