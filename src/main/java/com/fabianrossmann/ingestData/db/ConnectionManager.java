package com.fabianrossmann.ingestData.db;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionManager {
    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private final BasicDataSource dataSource = null;
    private String DB_URL = "jdbc:postgresql://%host%:%port%/%database%";
    private String DB_USER = "user";
    private String DB_PASSWORD = "password";

    public ConnectionManager(String host, int port, String database, String user, String password) {
        this.DB_URL = this.DB_URL.replaceAll("%host%", host);
        this.DB_URL = this.DB_URL.replaceAll("%port%", Integer.toString(port));
        this.DB_URL = this.DB_URL.replaceAll("%database%", database);
        this.DB_USER = user;
        this.DB_PASSWORD = password;
    }

    public BasicDataSource setupDataSource() {
        System.out.println("[INFO] Setting up Data Source");
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(DRIVER_CLASS_NAME);
        ds.setUrl(DB_URL);
        ds.setUsername(DB_USER);
        ds.setPassword(DB_PASSWORD);
        System.out.println("[INFO] Finished setting up Data Source");
        return ds;
    }


    public void closeDataSource(DataSource source) throws SQLException {
        BasicDataSource dataSource = (BasicDataSource) source;
        dataSource.close();
    }
}
