package com.epam.web.rotten.potatoes.connection;

import com.epam.web.rotten.potatoes.exceptions.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*package-private*/ class ConnectionFactory {
    private final static String DB_PROPERTIES = "database.properties";
    private final static String DB_DRIVER = "driver";
    private final static String DB_URL = "url";
    private final static String DB_USER = "user";
    private final static String DB_PASSWORD = "password";
    private static final String CAN_T_CREATE_CONNECTION = "Can't create connection";
    private static final String PROPERTIES_NOT_FOUND = "DB properties not found";

    private final Properties properties;

    /*private-package*/ ConnectionFactory() {
        properties = readProperties();
    }

    public Connection create() {
        String driver = properties.getProperty(DB_DRIVER);
        String url = properties.getProperty(DB_URL);
        String userName = properties.getProperty(DB_USER);
        String password = properties.getProperty(DB_PASSWORD);
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionPoolException(CAN_T_CREATE_CONNECTION, e);
        }
    }

    private Properties readProperties(){
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(DB_PROPERTIES)){
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new ConnectionPoolException(PROPERTIES_NOT_FOUND, e);
        }
    }
}