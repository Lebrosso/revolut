package com.jacek.rest;

import com.jacek.model.DbConnector;
import domain.Transaction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/transfer")
public class TransferController {
    @GET
    @Path("/listTransactions")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
    public List<Transaction> listTransactions() {
        try {
            Connection con = DbConnector.getConnection();
            DbConnector.createDatabse(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
