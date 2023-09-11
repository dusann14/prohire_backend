package com.example.demo.email;

import jakarta.mail.MessagingException;

public interface EmailSender {
    void send(String to, String link) throws MessagingException;
}
