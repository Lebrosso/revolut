package com.jacek.rest;

import domain.AccountDto;
import domain.TransactionDto;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;


@Path("/transfer")
public class TransferController {
    @GET
    @Path("/listTransactions")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
    public List<TransactionDto> listTransactions() {
        return TransferService.getInstance().listTransactions();
    }

    @GET
    @Path("/listAccounts")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
    public List<AccountDto> listAccounts() {
        return TransferService.getInstance().listAccounts();
    }

    @POST
    @Path("/makeTransfer/{amount}/{sourceAccount}/{destinationAccount}")
    public void makeTransfer(@NotNull @PathParam("amount") Long amount,@NotNull @PathParam("sourceAccount") Long sourceAccount,@NotNull @PathParam("destinationAccount") Long destinationAccount) throws SQLException {
        TransferService.getInstance().makeTransfer(amount, sourceAccount, destinationAccount);
    }
}
