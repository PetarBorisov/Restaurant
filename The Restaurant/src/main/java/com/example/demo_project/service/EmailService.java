package com.example.demo_project.service;

import java.util.Locale;

public interface EmailService {

    void sendRegistrationEmail(String userEmail, String userName, Locale preferredLocale);

    void sendReservationEmail(String userEmail, String userName, Locale preferredLocale);

    void sendEmail(String userEmail, String subject, String content);

    String getEmailSubject(String messageKey, Locale locale);

    String generateMessageContent(String templateName, Locale locale, String userName);
}
