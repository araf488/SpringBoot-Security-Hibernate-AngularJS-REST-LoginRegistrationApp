package com.spring.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.models.Contact;
import com.spring.repository.ContactsRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ContactServiceImpl implements ContactService {

    @Resource
    private ContactsRepository contactsRepository;

    @Override
    @Transactional
    public Contact create(Contact contact) {
        if (contact.getId() != null) {
            Contact c = contactsRepository.getOne(contact.getId());
            c.setId(contact.getId());
            c.setEmail(contact.getEmail());
            c.setName(contact.getName());
            c.setPhone(contact.getPhone());
            return contactsRepository.save(c);
        } else {
            return contactsRepository.save(contact);
        }
    }

    @Override
    @Transactional
    public List<Contact> findByUserId(String id) {
        List<Contact> contacts = contactsRepository.findByUser(id);
        Collections.sort(contacts, Contact.ContactNameComparator);
        return contacts;
    }

    @Override
    @Transactional(rollbackFor = ObjectNotFoundException.class)
    public Contact delete(Long id) throws ObjectNotFoundException {
        Contact deletedContact = contactsRepository.findOne(id);

        if (deletedContact == null) {
            throw new ObjectNotFoundException("No Element Found");
        }

        contactsRepository.delete(deletedContact);
        return deletedContact;
    }

    @Override
    @Transactional
    public List findAll() {
        return contactsRepository.findAll();
    }

    @Override
    public List<Contact> findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Contact> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
