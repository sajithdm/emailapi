package com.java.airnz.emailapi.service;

import com.java.airnz._project.artifactId_.model.Email;
import com.java.airnz._project.artifactId_.model.EmailStatus;
import com.java.airnz.emailapi.repository.EmailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;

    @Override
    public Long createEmail(Email email) {
        if (Objects.isNull(email)) {
            log.error("createEmail failed. Empty email!");
            return 0L;
        }
        return repository.createDraft(email).getId();
    }

    @Override
    public void updateEmail(Long id, Email updated) {
        if (Objects.isNull(updated)) {
            log.error("UpdateEmail failed. Empty email!");
        }
        repository.updateDraft(id, updated.status(EmailStatus.DRAFT));
    }

    @Override
    public boolean sendEmail(Long emailId) {
        if (emailId > 0) {
            return repository.sendEmail(emailId);
        }
        log.error("sendEmail. Email not found!");
        return false;
    }

    @Override
    public Optional<Email> getEmail(Long id) {
        if (id > 0) {
            return repository.findEmailById(id);
        }
        return null;
    }

    @Override
    public Optional<List<Email>> getEmailList(int pageNum, int pageSize, String status) {
        if (pageNum > 0 && pageSize > 0) {
            return repository.findEmailList(pageNum, pageSize, status);
        }
        return null;
    }

}
