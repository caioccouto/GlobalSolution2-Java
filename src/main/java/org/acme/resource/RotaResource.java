package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.RotaBO;
import org.acme.domain.Rota;

import java.sql.SQLException;
import java.util.List;

@Path("/rotas")
public class RotaResource {

    private final RotaBO rotaBO = new RotaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rota> listar() throws SQLException, ClassNotFoundException {
        return rotaBO.listarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        Rota r = rotaBO.buscarPorIdBo(id);
        if (r == null) return Response.status(Response.Status.NOT_FOUND).entity("Rota não encontrada.").build();
        return Response.ok(r).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Rota rota, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        try {
            rotaBO.inserirBo(rota);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(rota.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Rota rota) throws SQLException, ClassNotFoundException {
        try {
            rota.setId(id);
            rotaBO.atualizarBo(rota);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        rotaBO.deletarBo(id);
        return Response.ok().build();
    }
}