/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.esp.dit2.servicerest;

import dao.impl.ArticleDaoImpl;
import domaine.Article;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("article")
public class ArticleRessource {

    @PermitAll
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getArticles() {
        return Response.ok( new ArticleDaoImpl().getAll()).build();
    }
    @PermitAll
    @GET
    @Path("groupbycategorie")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Article> getArticlesGroupByCategorie() {
        return new ArticleDaoImpl().getListGroupeByCategorie();
    }
    @PermitAll
    @GET
    @Path("categorie/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Article> getArticlesByCategorie(@PathParam("id") Long id) {
        return new ArticleDaoImpl().getListByCategorie(id);
    }

    @RolesAllowed({"EDITEUR", "ADMINISTRATEUR"})
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Article create(Article article) {
        return new ArticleDaoImpl().create(article);
    }

    @RolesAllowed({"EDITEUR", "ADMINISTRATEUR"})
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Article update(Article article) {

        return new ArticleDaoImpl().update(article);
    }

    @RolesAllowed({"EDITEUR", "ADMINISTRATEUR"})
    @DELETE
    @Path("delete/{id}")
    public boolean delete(@PathParam("id") Long id) {
        return new ArticleDaoImpl().delete(id);
    }
    @PermitAll
    @GET
    @Path("{id}")
    public Article getById(@PathParam("id") Long id) {
        return new ArticleDaoImpl().getById(id);
    }

}
