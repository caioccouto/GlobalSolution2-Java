package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.EmpresaBO;
import org.acme.domain.Empresa;

import java.sql.SQLException;
import java.util.List;

@Path("/empresas")
public class EmpresaResource {

    private final EmpresaBO empresaBo = new EmpresaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> listar() throws SQLException, ClassNotFoundException {
        return empresaBo.listarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        Empresa empresa = empresaBo.buscarPorIdBo(id);
        if (empresa == null) return Response.status(Response.Status.NOT_FOUND).entity("Empresa não encontrada.").build();
        return Response.ok(empresa).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Empresa empresa, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        try {
            empresaBo.inserirBo(empresa);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(empresa.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Empresa empresa) throws SQLException, ClassNotFoundException {
        try {
            empresa.setId(id);
            empresaBo.atualizarBo(empresa);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) throws SQLException, ClassNotFoundException {
        empresaBo.deletarBo(id);
        return Response.ok().build();
    }
}
