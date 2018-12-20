package com.jacek.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import domain.TransactionDto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnector {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static Connection conn;

    private DbConnector() {
    }

    public static DbConnector instance = null;


    public static void loadData() throws SQLException, FileNotFoundException {
        Statement st = conn.createStatement();
        String text = null;
        try {
            text = new String(Files.readAllBytes(Paths.get("loadData.sql")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        st.executeUpdate(text);
    }

    public static DbConnector getInstance() throws SQLException, FileNotFoundException {
        if (instance == null) {
            config.setDriverClassName("org.h2.Driver");
            config.setJdbcUrl("jdbc:h2:mem:revol;DB_CLOSE_DELAY=-1;INIT=runscript from 'classpath:/createDb.sql'");
            config.setUsername("sa");
            config.setPassword("");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            ds = new HikariDataSource(config);
            conn = ds.getConnection();
            instance = new DbConnector();
            loadData();
            return instance;
        } else {
            return instance;
        }
    }

    public List<TransactionDto> getAllTransactions() throws SQLException, FileNotFoundException {

        List<TransactionDto> transactions = new ArrayList<>();
        Statement st = conn.createStatement();
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM BANK_TRANSACTION");
            while (rs.next()) {
                TransactionDto trans = new TransactionDto();
                trans.setId(rs.getLong("id"));
                trans.setTitle(rs.getString("title"));
                trans.setAmount(rs.getLong("amount"));
                trans.setSourceAcc(rs.getLong("sourceAcc"));
                trans.setDestinationAcc(rs.getLong("destinationAcc"));
                transactions.add(trans);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public Long makeTransfer(Long amount, Long sourceAccount, Long destinationAccount) throws SQLException {
        PreparedStatement st = conn.prepareStatement("UPDATE ACCOUNT SET credit = credit - ? WHERE id = ? ");
        st.setLong(1, amount);
        st.setLong(2, sourceAccount);
        int i = st.executeUpdate();

        PreparedStatement stm = conn.prepareStatement("UPDATE ACCOUNT SET credit = credit + ? WHERE id = ? ");
        stm.setLong(1, amount);
        stm.setLong(2, destinationAccount);
        int k = stm.executeUpdate();

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO BANK_TRANSACTION VALUES (id = 1 , title='test' , amount=amount , sourceAcc=sourceAccount, destinationAcc=destinationAccount ) ");
        stm.setLong(1, amount);
        stm.setLong(2, destinationAccount);
        int j = stm.executeUpdate();

        return null;
    }


}
