package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.AlertaAmbientalBO;
import org.acme.domain.AlertaAmbiental;

import java.sql.SQLException;
import java.util.List;

@Path("/alertas")
public class AlertaAmbientalResource {

    private final AlertaAmbientalBO alertaBO = new AlertaAmbientalBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AlertaAmbiental> listar() throws SQLException, ClassNotFoundException {
        return alertaBO.listarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        AlertaAmbiental a = alertaBO.buscarPorIdBo(id);
        if (a == null) return Response.status(Response.Status.NOT_FOUND).entity("Alerta não encontrado.").build();
        return Response.ok(a).build();
    }
}