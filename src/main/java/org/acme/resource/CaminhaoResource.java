package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.CaminhaoBO;
import org.acme.domain.Caminhao;

import java.sql.SQLException;
import java.util.List;

@Path("/caminhoes")
public class CaminhaoResource {

    private final CaminhaoBO caminhaoBO = new CaminhaoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Caminhao> listar() throws SQLException, ClassNotFoundException {
        return caminhaoBO.listarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        Caminhao c = caminhaoBO.buscarPorIdBo(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).entity("Caminhão não encontrado.").build();
        return Response.ok(c).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Caminhao caminhao, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        try {
            caminhaoBO.inserirBo(caminhao);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(caminhao.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Caminhao caminhao) throws SQLException, ClassNotFoundException {
        try {
            caminhao.setId(id);
            caminhaoBO.atualizarBo(caminhao);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        caminhaoBO.deletarBo(id);
        return Response.ok().build();
    }
}