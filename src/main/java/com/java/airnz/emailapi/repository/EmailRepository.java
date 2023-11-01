package com.java.airnz.emailapi.repository;

import com.java.airnz._project.artifactId_.model.Email;

import java.util.List;
import java.util.Optional;

/**
 * Email repository interface of the Email API
 */
public interface EmailRepository {

    public Email createDraft(Email email);

    public Optional<Email> findEmailById(Long emailId);

    public Optional<List<Email>> findEmailList(int pageNum, int pageSize, String status);

    public Email updateDraft(Long id, Email email);

    public boolean sendEmail(Long emailId);
}
