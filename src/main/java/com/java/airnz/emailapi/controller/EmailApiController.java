package com.java.airnz.emailapi.controller;

import com.java.airnz._project.artifactId_.api.EmailApi;
import com.java.airnz._project.artifactId_.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class EmailApiController implements EmailApi {

    @Override
    public ResponseEntity<Void> createEmail(String token, Email email) {
        return EmailApi.super.createEmail(token, email);
    }

    @Override
    public ResponseEntity<Email> getEmailById(String token, Long id) {
        return EmailApi.super.getEmailById(token, id);
    }

    @Override
    public ResponseEntity<List<Email>> getEmailList(String token, String status, Integer pageNumber, Integer pageSize) {
        return EmailApi.super.getEmailList(token, status, pageNumber, pageSize);
    }

    @Override
    public ResponseEntity<Void> patchEmail(String token, Long id, String body) {
        return EmailApi.super.patchEmail(token, id, body);
    }

    @Override
    public ResponseEntity<Void> updateEmail(String token, Long id, Email email) {
        return EmailApi.super.updateEmail(token, id, email);
    }
}
