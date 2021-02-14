package com.epam.jwd.config;

import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The type Mail thread.
 */
public class MailThread extends Thread {
    private static final Logger logger = Logger.getLogger(MailThread.class);

    private MimeMessage message;
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;

    /**
     * Instantiates a new Mail thread.
     *
     * @param sendToEmail mail of destination
     * @param mailSubject subject of mail
     * @param mailText    mail text
     */
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
            logger.error("Cannot create e-mail message", e);
        }
    }
    public void run() {
        init();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error occurred delivering e-mail message", e);
        }
    }
}
