package com.example.groupfour.service;

import com.example.groupfour.entity.Email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
    void sendEmail(Email email) throws MessagingException;

    void resetPassword(String email, String token) throws MessagingException;
}
