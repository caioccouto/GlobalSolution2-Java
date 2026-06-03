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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Combustivel combustivel, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        try {
            combustivelBO.inserirBo(combustivel);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(combustivel.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Combustivel combustivel) throws SQLException, ClassNotFoundException {
        try {
            combustivel.setId(id);
            combustivelBO.atualizarBo(combustivel);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        combustivelBO.deletarBo(id);
        return Response.ok().build();
    }
}