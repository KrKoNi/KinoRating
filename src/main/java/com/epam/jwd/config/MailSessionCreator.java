package com.epam.jwd.config;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class MailSessionCreator {
    private final String smtpHost;
    private final String smtpPort;
    private final String username;
    private final String password;
    private final Properties mailSessionProperties;

    public MailSessionCreator() {
        smtpHost = MailProperties.getMailSmtpHost();
        smtpPort = MailProperties.getMailSmtpPort();
        username = MailProperties.getMailUserName();
        password = MailProperties.getMailUserPassword();

        mailSessionProperties = new Properties();
        mailSessionProperties.setProperty("mail.transport.protocol", "smtp");
        mailSessionProperties.setProperty("mail.host", smtpHost);
        mailSessionProperties.put("mail.smtp.auth", "true");
        mailSessionProperties.put("mail.smtp.starttls.enable", "true");
        mailSessionProperties.put("mail.smtp.port", smtpPort);
    }

    /**
     * Create session session.
     *
     * @return the session
     */
    public Session createSession() {
        return Session.getInstance(mailSessionProperties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }
}
