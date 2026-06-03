package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.EmissaoBO;
import org.acme.domain.Emissao;

import java.sql.SQLException;
import java.util.List;

@Path("/emissoes")
public class EmissaoResource {

    private final EmissaoBO emissaoBO = new EmissaoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Emissao> listar() throws SQLException, ClassNotFoundException {
        return emissaoBO.listarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        Emissao e = emissaoBO.buscarPorIdBo(id);
        if (e == null) return Response.status(Response.Status.NOT_FOUND).entity("Emissão não encontrada.").build();
        return Response.ok(e).build();
    }
}