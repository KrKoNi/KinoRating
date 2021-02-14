package com.epam.jwd.config;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Util class for reading and storing mail parameters
 */
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

    /**
     * Gets mail smtp host.
     *
     * @return the mail smtp host
     */
    public static String getMailSmtpHost() {
        return mailSmtpHost;
    }

    /**
     * Gets mail smtp port.
     *
     * @return the mail smtp port
     */
    public static String getMailSmtpPort() {
        return mailSmtpPort;
    }

    /**
     * Gets mail linked to application.
     *
     * @return the mail user name
     */
    public static String getMailUserName() {
        return mailUserName;
    }

    /**
     * Gets password of mail linked to application.
     *
     * @return the mail user password
     */
    public static String getMailUserPassword() {
        return mailUserPassword;
    }
}
