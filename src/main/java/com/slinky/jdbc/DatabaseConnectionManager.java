package com.slinky.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private final String url;
    private final Properties properties;

    /**
     * often these will be brought in with a configuration mechanism such as an properties file. doing it this way simplifies the work for the tutorial.
     * @param host being host.
     * @param databaseName db name.
     * @param username bring username.
     * @param password host pw.
     */
    public DatabaseConnectionManager(
            String host, String databaseName,
            String username, String password
    ) {
        this.url = "jdbc:postgresql://"+host+"/"+databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }
}
