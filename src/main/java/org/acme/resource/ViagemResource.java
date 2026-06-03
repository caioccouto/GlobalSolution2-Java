package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.ViagemBO;
import org.acme.domain.Viagem;

import java.sql.SQLException;
import java.util.List;

@Path("/viagens")
public class ViagemResource {

    private final ViagemBO viagemBO = new ViagemBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Viagem> listar() throws SQLException, ClassNotFoundException {
        return viagemBO.listarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        Viagem v = viagemBO.buscarPorIdBo(id);
        if (v == null) return Response.status(Response.Status.NOT_FOUND).entity("Viagem não encontrada.").build();
        return Response.ok(v).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Viagem viagem, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        try {
            viagemBO.inserirBo(viagem);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(viagem.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Viagem viagem) throws SQLException, ClassNotFoundException {
        try {
            viagem.setId(id);
            viagemBO.atualizarBo(viagem);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        viagemBO.deletarBo(id);
        return Response.ok().build();
    }
}