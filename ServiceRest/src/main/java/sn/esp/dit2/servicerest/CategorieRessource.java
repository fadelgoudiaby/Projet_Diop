/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.esp.dit2.servicerest;

import dao.impl.CategorieDaoImpl;
import domaine.Categorie;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 */
@Path("/categorie")
public class CategorieRessource {

    @RolesAllowed("EDITEUR")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("test1")
    public Response ed() {
        return Response.ok("editeur").build();
    }

    @RolesAllowed("ADMINISTRATEUR")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("test2")
    public Response ad() {
        return Response.ok("admin").build();
    }

    @RolesAllowed({"EDITEUR", "ADMINISTRATEUR"})
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Categorie create(Categorie t) {
        return new CategorieDaoImpl().create(t);
    }

    @RolesAllowed({"EDITEUR", "ADMINISTRATEUR"})
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Categorie update(Categorie categorie) {

        return new CategorieDaoImpl().update(categorie);
    }

    @RolesAllowed({"EDITEUR", "ADMINISTRATEUR"})
    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean delete(@PathParam("id") Long id) {
        return new CategorieDaoImpl().delete(id);
    }

    @GET
    @PermitAll
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll() {
        return Response.ok(new CategorieDaoImpl().getAll())
                .build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @PermitAll
    public Categorie getById(@PathParam("id") Long id) {
        return new CategorieDaoImpl().getById(id);
    }

}
