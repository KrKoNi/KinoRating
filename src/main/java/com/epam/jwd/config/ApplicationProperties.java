package com.epam.jwd.config;

public class ApplicationProperties {

    private static final String url = "jdbc:mysql://localhost:3306/kinorating";
    private static final String user = "root";
    private static final String password = "meowmeow";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
