package com.github.ki3lmigu3l.email.service.impl;

import com.github.ki3lmigu3l.email.enums.StatusEmail;
import com.github.ki3lmigu3l.email.model.Email;
import com.github.ki3lmigu3l.email.repository.EmailRepository;
import com.github.ki3lmigu3l.email.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {

    final EmailRepository emailRepository;
    final JavaMailSender emailSender;

    public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEmail(Email email) {
        try {
            email.setSendDateEmail(LocalDateTime.now());
            email.setEmailFrom(this.emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException mailException) {
            email.setStatusEmail(StatusEmail.ERROR);
            System.out.println("Erro ao enviar e-mail: " + mailException.getMessage());
        } finally {
            return emailRepository.save(email);
        }
    }
}
