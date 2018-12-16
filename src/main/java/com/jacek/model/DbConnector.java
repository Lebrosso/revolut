package com.jacek.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnector {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setDriverClassName("org.h2.Driver");
        System.out.println(config.getDriverClassName());
        config.setJdbcUrl("jdbc:h2:mem:revol;DB_CLOSE_DELAY=-1;INIT=runscript from 'classpath:/createDb.sql'");
        config.setUsername("sa");
        config.setPassword("");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);

    }

    private DbConnector() {
    }

    public static DbConnector instance = null;

    public static DbConnector getInstance() {
        if (instance == null) {
            return new DbConnector();
        } else {
            return instance;
        }
    }

    public Connection getAllTransactions() throws SQLException, FileNotFoundException {

        Statement st = ds.getConnection().createStatement();
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM BANK_TRANSACTION");
            while (rs.next()) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
