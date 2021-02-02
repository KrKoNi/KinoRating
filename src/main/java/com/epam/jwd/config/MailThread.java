package com.epam.jwd.config;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailThread extends Thread {
    private MimeMessage message;
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;

    public MailThread(String sendToEmail,String mailSubject, String mailText) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
    }

    private void init() {
        Session mailSession = (new MailSessionCreator()).createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(mailSubject);
            message.setContent(mailText, "text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        init();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
