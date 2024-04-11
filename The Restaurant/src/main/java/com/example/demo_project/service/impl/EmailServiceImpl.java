package com.example.demo_project.service.impl;

import com.example.demo_project.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final MessageSource messageSource;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender, MessageSource messageSource) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
    }

    public void sendRegistrationEmail(String userEmail, String userName, Locale preferredLocale) {
        sendEmail(userEmail, getEmailSubject("registration_subject", preferredLocale),
                generateMessageContent("email/registration", preferredLocale, userName));
    }

    public void sendReservationEmail(String userEmail, String userName, Locale preferredLocale) {
        sendEmail(userEmail, getEmailSubject("reservation_subject", preferredLocale),
                generateMessageContent("email/success_reservation", preferredLocale, userName));
    }

    public void sendEmail(String userEmail, String subject, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("the@restaurant.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailSubject(String messageKey, Locale locale) {
        return messageSource.getMessage(messageKey, new Object[0], locale);
    }

    public String generateMessageContent(String templateName, Locale locale, String userName) {
        Context context = new Context();
        context.setLocale(locale);
        context.setVariable("userName", userName);
        return templateEngine.process(templateName, context);
    }
}