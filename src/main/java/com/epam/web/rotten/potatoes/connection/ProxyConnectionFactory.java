package com.epam.web.rotten.potatoes.connection;

import com.epam.web.rotten.potatoes.exceptions.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*package-private*/ class ProxyConnectionFactory {
    private Properties properties;
    private static final String DATABASE_PROPERTIES_PATH = "database.properties";
    private static final String DATABASE_URL_KEY = "url";
    private String databaseUrl;
    private int poolSize;
    private ConnectionPool connectionPool;

    /*package-private*/ ProxyConnectionFactory(ConnectionPool connectionPool) throws ConnectionPoolException {
        this.connectionPool = connectionPool;
        properties = new Properties();
        Class<ProxyConnectionFactory> clazz = ProxyConnectionFactory.class;
        ClassLoader classLoader = clazz.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(DATABASE_PROPERTIES_PATH)) {
            properties.load(inputStream);
            String driver = properties.getProperty("driver");
            databaseUrl = properties.getProperty(DATABASE_URL_KEY);
            poolSize = Integer.parseInt(properties.getProperty("poolsize"));
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

    /*package-private*/ ProxyConnection create() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(databaseUrl, properties);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        return new ProxyConnection(connection, connectionPool);
    }

    /*package-private*/ int getPoolSize() {
        return poolSize;
    }
}
