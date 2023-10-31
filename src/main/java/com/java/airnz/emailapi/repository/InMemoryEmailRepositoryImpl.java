package com.java.airnz.emailapi.repository;

import com.java.airnz._project.artifactId_.model.Email;
import com.java.airnz._project.artifactId_.model.EmailStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryEmailRepositoryImpl implements EmailRepository {

    private Map<Long, Email> emailMap = new HashMap<>();
    private long nextId;

    public Email createDraft(Email email) {
        if (emailMap.isEmpty()) {
            nextId = 1L;
        }
        emailMap.put(nextId, email.id(nextId));
        nextId++;
        return email;
    }

    public Optional<Email> findEmailById(Long emailId) {
        return Optional.ofNullable(emailMap.get(emailId));
    }

    public Optional<List<Email>> findEmailList(int pageNum, int pageSize, String status) {
        long start = ((pageNum - 1) * pageSize) + 1;
        long existingEmailCount = emailMap.values().stream().filter(e -> e.getStatus().getValue().equalsIgnoreCase(status)).count();

        if (start > existingEmailCount) {
            return Optional.empty();
        }

        List<Email> returnList = emailMap.values().stream().limit(pageSize).toList();
        return Optional.ofNullable(returnList);
    }

    public Email updateDraft(Long id, Email email) {
        emailMap.put(id, email);
        return email;
    }

    public boolean sendEmail(Long emailId) {
        Email draft = emailMap.get(emailId);
        if (draft == null || draft.getStatus().getValue().equals(EmailStatus.SENT)) {
            return false;
        }
        emailMap.put(emailId, draft.status(EmailStatus.SENT));
        return true;
    }

}
