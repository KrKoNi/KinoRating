package com.epam.jwd.config;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

    public static String getUrl() {
        return url;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUser() {
        return user;
    }

}
