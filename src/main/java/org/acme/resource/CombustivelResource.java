package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.CombustivelBO;
import org.acme.domain.Combustivel;

import java.sql.SQLException;
import java.util.List;

@Path("/combustiveis")
public class CombustivelResource {

    private final CombustivelBO combustivelBO = new CombustivelBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Combustivel> listar() throws SQLException, ClassNotFoundException {
        return combustivelBO.listarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        Combustivel c = combustivelBO.buscarPorIdBo(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).entity("Combustível não encontrado.").build();
        return Response.ok(c).build();
    }
}