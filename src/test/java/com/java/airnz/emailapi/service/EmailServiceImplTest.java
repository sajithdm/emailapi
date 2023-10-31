package com.java.airnz.emailapi.service;

import com.java.airnz._project.artifactId_.model.Email;
import com.java.airnz._project.artifactId_.model.EmailStatus;
import com.java.airnz.emailapi.repository.EmailRepository;
import com.java.airnz.emailapi.repository.InMemoryEmailRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmailServiceImplTest {

    private EmailService emailService;
    private EmailRepository emailRepository;

    @BeforeEach
    void setUp() {
        emailRepository = Mockito.mock(InMemoryEmailRepositoryImpl.class);
        emailService = new EmailServiceImpl(emailRepository);
    }

    @Test
    @DisplayName("Given valid email model, when creating email, then creating with correct ID")
    void createEmailTest() {
        final Email draftVersion = new Email().subject("This is test email").status(EmailStatus.DRAFT);
        final Email savedVersion = new Email().subject("This is test email").status(EmailStatus.DRAFT).id(1L);
        when(emailRepository.createDraft(any())).thenReturn(savedVersion);
        assertEquals(1, emailService.createEmail(draftVersion));
    }

    @Test
    @DisplayName("Given valid email id, when retrieve email, then return correct email")
    void getEmailTest() {
        final Email email = new Email().id(1L).subject("This is test email").status(EmailStatus.DRAFT);
        when(emailRepository.findEmailById(any())).thenReturn(Optional.ofNullable(email));
        Optional<Email> optionalEmail = emailService.getEmail(1L);
        assertNotNull(optionalEmail);
        assertEquals("This is test email", optionalEmail.get().getSubject());
    }

    @Test
    @DisplayName("Given valid email list, when retrieve email list, then return correct email list ")
    void getEmailListTest() {
        final List<Email> emailList = Arrays.asList(new Email().id(1L).subject("This is test email1").status(EmailStatus.DRAFT), new Email().id(2L).subject("This is test email2").status(EmailStatus.DRAFT));
        when(emailRepository.findEmailList(1, 25, "draft")).thenReturn(Optional.ofNullable(emailList));
        Optional<List<Email>> optionalEmailList = emailService.getEmailList(1, 25, "draft");
        assertNotNull(optionalEmailList);
        assertEquals(2, optionalEmailList.get().size());
    }

    @Test
    @DisplayName("Given valid draft, when update email, then updating with correct details")
    void updateEmailTest() {
        final Email draftVersion = new Email().subject("This is test email").status(EmailStatus.DRAFT).id(1L);
        final Email updatedVersion = new Email().subject("This is updated version").status(EmailStatus.DRAFT).id(1L);
        when(emailRepository.updateDraft(1L, draftVersion)).thenReturn(updatedVersion);
        emailService.updateEmail(1L, draftVersion);
    }

    @Test
    @DisplayName("Given valid email, when send email, then update status to SENT")
    void sendEmailTest() {
        when(emailRepository.sendEmail(1L)).thenReturn(true);
        assertTrue(emailService.sendEmail(1L));
    }

}
