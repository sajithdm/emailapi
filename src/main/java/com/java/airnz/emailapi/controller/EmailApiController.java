package com.java.airnz.emailapi.controller;

import com.java.airnz._project.artifactId_.api.EmailApi;
import com.java.airnz._project.artifactId_.model.Email;
import com.java.airnz.emailapi.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Controller implementation of Email API. Parent interface has been
 * generated through openapi-generator.
 */
@RestController
@Slf4j
public class EmailApiController implements EmailApi {

    @Autowired
    EmailService service;
    @Override
    public ResponseEntity<Void> createEmail(Email email) {
        Long emailId = service.createEmail(email);

        if (emailId == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email creation failed");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("location-url", "/v1/emails/" + emailId);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Email> getEmailById(Long id) {

        Email email = service.getEmail(id).orElse(null);

        if (ObjectUtils.isEmpty(email)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not available with given ID");
        }
            return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Email>> getEmailList(String status, Integer pageNumber, Integer pageSize) {

        List<Email> emailList = service.getEmailList(pageNumber,pageSize,status).orElse(null);
        if (ObjectUtils.isEmpty(emailList)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email list not available with given details");
        }
        return new ResponseEntity<>(emailList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateEmail(Long id, Email email) {

        if (ObjectUtils.isEmpty(id) || ObjectUtils.isEmpty(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty email ID or body!");
        }
        service.updateEmail(id, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> sendEmail( Long id) {
        service.sendEmail(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
