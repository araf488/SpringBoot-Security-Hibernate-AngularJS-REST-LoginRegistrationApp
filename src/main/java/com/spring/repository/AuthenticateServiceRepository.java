package com.spring.repository;

import org.springframework.data.repository.Repository;

import com.spring.models.User;

@org.springframework.stereotype.Repository
public interface AuthenticateServiceRepository extends Repository<User, String> {

	User findByEmail(String email);

}
