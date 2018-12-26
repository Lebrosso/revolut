package com.jacek.rest;

import com.jacek.model.DbConnector;
import domain.AccountDto;
import domain.TransactionDto;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;


@Path("/transfer")
public class TransferController {
    @GET
    @Path("/listTransactions")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
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

    @GET
    @Path("/listAccounts")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
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

    @POST
    @Path("/makeTransfer/{amount}/{sourceAccount}/{destinationAccount}")
    public void makeTransfer(@NotNull @PathParam("amount") Long amount,@NotNull @PathParam("sourceAccount") Long sourceAccount,@NotNull @PathParam("destinationAccount") Long destinationAccount) throws SQLException {
        DbConnector db = DbConnector.getInstance();
        db.makeTransfer(amount,sourceAccount,destinationAccount);
    }
}
