package com.java.airnz.emailapi.controller;

import com.java.airnz._project.artifactId_.api.EmailApi;
import com.java.airnz._project.artifactId_.model.Email;
import com.java.airnz.emailapi.service.EmailService;
import com.java.airnz.emailapi.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class EmailApiController implements EmailApi {

    @Autowired
    EmailService service;
    @Override
    public ResponseEntity<Void> createEmail(String token, Email email) {

        if (!Validator.authenticate(token)) {
            return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
        }

       Long emailId = service.createEmail(email);
        if (emailId == 0) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("location-url", "/v1/emails/"+ emailId);
        return new ResponseEntity<> (headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Email> getEmailById(String token, Long id) {
        if (!Validator.authenticate(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Email email = service.getEmail(id).orElse(null);

        if (ObjectUtils.isEmpty(email)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Email>> getEmailList(String token, String status, Integer pageNumber, Integer pageSize) {
        if (!Validator.authenticate(token)) {
            return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
        }
        List<Email> emailList = service.getEmailList(pageNumber,pageSize,status).orElse(null);
        if (ObjectUtils.isEmpty(emailList)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(emailList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateEmail(String token, Long id, Email email) {
        if (!Validator.authenticate(token)) {
            return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
        }
        service.updateEmail(id, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> sendEmail(String token, Long id) {
        if (!Validator.authenticate(token)) {
            return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
        }
        service.sendEmail(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
