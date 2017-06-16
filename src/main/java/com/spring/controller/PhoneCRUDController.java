package com.spring.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.constants.Constant;
import com.spring.models.Contact;
import com.spring.service.AuthenticateService;
import com.spring.service.ContactService;

import io.jsonwebtoken.Jwts;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class PhoneCRUDController {

    private Set<Contact> contactList = new HashSet<Contact>();

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    AuthenticateService authenticateService;

    public PhoneCRUDController() {
    }

    @RequestMapping(value = "/data/saveContact", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Contact> save(@RequestBody Contact contact, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        String userid = Jwts.parser().setSigningKey(Constant.KEY).parseClaimsJws(token).getBody().get("jti").toString();
        Map<String, Contact> saved = new HashMap<String, Contact>();
        contact.setUser(userid);
        Contact savedContact = contactService.create(contact);
        saved.put("data", savedContact);
        return saved;
    }

    @RequestMapping(value = "/data/deleteContact", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(@RequestBody Contact contact) throws ObjectNotFoundException {

        Map<String, String> returnStatus = new HashMap<String, String>();
        returnStatus.put("data", "deleted");
        contactService.delete(contact.getId());
        return returnStatus;
    }

    @RequestMapping(value = "/data/getContact", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getContacts(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        List<Contact> contacts = contactService.findByUserId(Jwts.parser().setSigningKey(Constant.KEY).parseClaimsJws(token).getBody().get("jti").toString());
        return contacts;
    }

}
