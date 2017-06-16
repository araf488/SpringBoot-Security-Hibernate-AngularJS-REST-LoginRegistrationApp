package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import com.spring.models.User;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
