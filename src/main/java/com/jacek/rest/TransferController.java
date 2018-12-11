package com.jacek.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transfer")
public class TransferController {
    @GET
    @Path("/listTransactions")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String listTransactions() {
        System.out.println("test");
        return null;
    }
}
