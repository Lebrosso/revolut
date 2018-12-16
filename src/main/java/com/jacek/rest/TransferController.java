package com.jacek.rest;

import com.jacek.model.DbConnector;
import domain.TransactionDto;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
            transactions = db.getAllTransactions();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @GET
    @Path("/makeTransfer/{amount}/{sourceAccount}/{destinationAccount}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
    public void makeTransfer(@NotNull @PathParam("amount") Long amount,@NotNull @PathParam("sourceAccount") Long sourceAccount,@NotNull @PathParam("destinationAccount") Long destinationAccount) throws SQLException {
        DbConnector db = DbConnector.getInstance();
        db.makeTransfer(amount,sourceAccount,destinationAccount);
    }
}
