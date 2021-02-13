package com.epam.jwd.config;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailProperties {

    private static final Logger logger = Logger.getLogger(MailProperties.class);

    private MailProperties() {

    }

    private static String mailSmtpHost;
    private static String mailSmtpPort;
    private static String mailUserName;
    private static String mailUserPassword;

    static {
        try (InputStream input = MailProperties.class.getClassLoader().getResourceAsStream("mail.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            mailSmtpHost = prop.getProperty("mail.smtp.host");
            mailSmtpPort = prop.getProperty("mail.smtp.port");
            mailUserName = prop.getProperty("mail.user.name");
            mailUserPassword = prop.getProperty("mail.user.password");

        } catch (IOException ex) {
            logger.error("Error reading mail props from file", ex);
        }
    }

    public static String getMailSmtpHost() {
        return mailSmtpHost;
    }

    public static String getMailSmtpPort() {
        return mailSmtpPort;
    }

    public static String getMailUserName() {
        return mailUserName;
    }

    public static String getMailUserPassword() {
        return mailUserPassword;
    }
}
