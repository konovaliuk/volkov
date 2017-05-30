package training.ua.dao.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {

    private static ConnectionPool ourInstance = new ConnectionPool();

    private BasicDataSource dataSource = new BasicDataSource();

    public static ConnectionPool getInstance() {
        return ourInstance;
    }

    private ConnectionPool() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(new File("src/main/resources/database.properties")));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setUrl(properties.getProperty("url"));

            dataSource.setInitialSize(Integer.valueOf(properties.getProperty("initial_size")));
            dataSource.setMaxTotal(Integer.valueOf(properties.getProperty("max_total")));
            dataSource.setMinIdle(Integer.valueOf(properties.getProperty("min_idle")));
            dataSource.setMaxIdle(Integer.valueOf(properties.getProperty("max_idle")));
            dataSource.setMaxOpenPreparedStatements(Integer.valueOf(properties.getProperty("max_open_prepared_statement")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
