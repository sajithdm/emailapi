package com.java.airnz.emailapi.service;

import com.java.airnz._project.artifactId_.model.Email;

import java.util.List;
import java.util.Optional;

/**
 * Service interface of Email API
 */
public interface EmailService {

    public Long createEmail(Email email);

    public Optional<Email> getEmail(Long id);

    public Optional<List<Email>> getEmailList(int pageNum, int pageSize, String status);

    public void updateEmail(Long id, Email email);

    public boolean sendEmail(Long emailId);

}
