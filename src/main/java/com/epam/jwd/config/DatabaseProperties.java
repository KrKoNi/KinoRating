package com.epam.jwd.config;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Util class for reading and storing database parameters
 */
public class DatabaseProperties {

    private static final Logger logger = Logger.getLogger(DatabaseProperties.class);

    private DatabaseProperties() {

    }

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Error registering JDBC driver", e);
        }
    }

    static {
        try (InputStream input = DatabaseProperties.class.getClassLoader().getResourceAsStream("database.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");


        } catch (IOException ex) {
            logger.error("Error reading properties from file", ex);
        }
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public static String getUser() {
        return user;
    }

}
