package com.java.airnz.emailapi.repository;

import com.java.airnz._project.artifactId_.model.Email;
import com.java.airnz._project.artifactId_.model.EmailStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InMemoryEmailRepositoryImplTest {

    private EmailRepository emailRepository;
    private Email testEmail1;
    private Email testEmail2;

    @BeforeEach
    void setUp() {
        emailRepository = new InMemoryEmailRepositoryImpl();
        testEmail1 = new Email().subject("This is test email").body("Test email body").status(EmailStatus.DRAFT).recipients(Arrays.asList("test1@gmail.com", "test2@yahoo.com"));
        testEmail2 = new Email().subject("This is another test email").body("Test email body").status(EmailStatus.DRAFT).recipients(Arrays.asList("test3@gmail.com", "test4@yahoo.com"));
    }

    @Test
    @DisplayName("Given valid email model, when create new email, then email created successfully")
    void createDraftTest() {
        final Email draft = emailRepository.createDraft(testEmail1);
        assertEquals(1, draft.getId());
    }

    @Test
    @DisplayName("Given valid email id, when retrieve email, then correct email retrieved")
    void findEmailByIdTest() {
        emailRepository.createDraft(testEmail1);
        Optional<Email> email = emailRepository.findEmailById(1L);
        assertNotNull(email);
        assertEquals(1, email.get().getId());
    }

    @Test
    @DisplayName("Given email list present, when retrieve email list, then correct email list retrieved")
    void findEmailListTest() {
        emailRepository.createDraft(testEmail1);
        emailRepository.createDraft(testEmail2);
        Optional<List<Email>> emailList = emailRepository.findEmailList(1, 25, "draft");
        assertNotNull(emailList);
        assertEquals(2, emailList.get().size());
    }

    @Test
    @DisplayName("Given valid email, when update email, then updated successfully")
    void UpdateDraftTest() {
        emailRepository.createDraft(testEmail1);
        emailRepository.createDraft(testEmail2);
        emailRepository.updateDraft(2L, testEmail2.body("Email body updated"));
        Optional<Email> email = emailRepository.findEmailById(2L);
        assertNotNull(email);
        assertEquals("Email body updated", email.get().getBody());
    }

    @Test
    @DisplayName("Given valid email, when send email, then status updated to SENT successfully")
    void sendEmailTest() {
        emailRepository.createDraft(testEmail1);
        emailRepository.sendEmail(1L);
        Optional<Email> email = emailRepository.findEmailById(1L);
        assertNotNull(email);
        assertEquals("SENT", email.get().getStatus().getValue());
    }

}
