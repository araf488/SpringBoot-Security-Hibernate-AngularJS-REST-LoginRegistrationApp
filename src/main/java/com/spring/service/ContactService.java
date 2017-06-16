package com.spring.service;

import java.util.List;

import com.spring.models.Contact;

import javassist.tools.rmi.ObjectNotFoundException;

public interface ContactService {

    Contact create(Contact contact);

    Contact delete(Long id) throws ObjectNotFoundException;

    List<Contact> findAll();

    List<Contact> findById(Long id);

    List<Contact> findById(String id);

    List<Contact> findByUserId(String id);

}
