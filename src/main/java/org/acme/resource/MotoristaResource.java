package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.MotoristaBO;
import org.acme.domain.Motorista;

import java.sql.SQLException;
import java.util.List;

@Path("/motoristas")
public class MotoristaResource {

    private final MotoristaBO motoristaBO = new MotoristaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Motorista> listar() throws SQLException, ClassNotFoundException {
        return motoristaBO.listarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        Motorista m = motoristaBO.buscarPorIdBo(id);
        if (m == null) return Response.status(Response.Status.NOT_FOUND).entity("Motorista não encontrado.").build();
        return Response.ok(m).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Motorista motorista, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        try {
            motoristaBO.inserirBo(motorista);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(motorista.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Motorista motorista) throws SQLException, ClassNotFoundException {
        try {
            motorista.setId(id);
            motoristaBO.atualizarBo(motorista);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        motoristaBO.deletarBo(id);
        return Response.ok().build();
    }
}