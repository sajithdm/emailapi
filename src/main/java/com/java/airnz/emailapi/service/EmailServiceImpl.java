package com.java.airnz.emailapi.service;

import com.java.airnz._project.artifactId_.model.Email;
import com.java.airnz._project.artifactId_.model.EmailStatus;
import com.java.airnz.emailapi.exception.EmailAPIException;
import com.java.airnz.emailapi.repository.EmailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service implementation of the Email API
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;

    @Override
    public Long createEmail(Email email) throws EmailAPIException {
        if (Objects.isNull(email)) {
            log.error("createEmail failed. Empty email!");
            throw new EmailAPIException(400, "Empty email");
        }
        return repository.createDraft(email).getId();
    }

    @Override
    public void updateEmail(Long id, Email updated) throws EmailAPIException {
        if (invalidId(id) || invalidEmail(updated)) {
            throw new EmailAPIException(400, "Invalid id or empty email.");
        }
        repository.updateDraft(id, updated.status(EmailStatus.DRAFT));
    }

    @Override
    public boolean sendEmail(Long id) throws EmailAPIException {
        if (invalidId(id)) {
            throw new EmailAPIException(400, "Invalid Empty Id.");
        }
        return repository.sendEmail(id);
    }

    @Override
    public Optional<Email> getEmail(Long id) throws EmailAPIException {
        if (invalidId(id)) {
            throw new EmailAPIException(400, "Invalid Empty Id.");
        }
        return repository.findEmailById(id);
    }

    @Override
    public Optional<List<Email>> getEmailList(int pageNum, int pageSize, String status) throws EmailAPIException {
        if (pageNum < 1 || pageSize < 1) {
            throw new EmailAPIException(400, "Invalid page details.");
        }
        return repository.findEmailList(pageNum, pageSize, status);
    }

    private boolean invalidId(Long id) {
        if (Objects.isNull(id) || id < 1) {
            log.error("Invalid Empty Id");
            return true;
        }
        return false;
    }

    private boolean invalidEmail(Email email) {
        if (Objects.isNull(email) || ObjectUtils.isEmpty(email)) {
            log.error("Empty email");
            return true;
        }
        return false;
    }

}
