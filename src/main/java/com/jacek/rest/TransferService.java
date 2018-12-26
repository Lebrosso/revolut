package com.jacek.rest;

import com.jacek.model.DbConnector;
import domain.AccountDto;
import domain.TransactionDto;
import java.sql.SQLException;
import java.util.List;

public class TransferService {

    public static TransferService instance = null;

    public static TransferService getInstance(){
        if (instance == null) {

            instance = new TransferService();
            return instance;
        } else {
            return instance;
        }
    }

        public List<TransactionDto> listTransactions() {
            List<TransactionDto> transactions = null;
            try {
                DbConnector db = DbConnector.getInstance();
                transactions = db.getTransactions();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return transactions;
        }


        public List<AccountDto> listAccounts() {
            List<AccountDto> accounts = null;
            try {
                DbConnector db = DbConnector.getInstance();
                accounts = db.getAccounts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return accounts;
        }


        public void makeTransfer(Long amount, Long sourceAccount, Long destinationAccount) throws SQLException {
            DbConnector db = DbConnector.getInstance();
            db.makeTransfer(amount, sourceAccount, destinationAccount);
        }

}

