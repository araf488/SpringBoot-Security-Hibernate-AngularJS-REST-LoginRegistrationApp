package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.Contact;

public interface ContactsRepository extends JpaRepository<Contact, Long> {

    public List<Contact> findByUser(String user);

    public Contact findByPhone(Long phone);

}
