/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.esp.dit2.servicerest;

import dao.impl.UserDaoImpl;
import domaine.User;
import java.util.List;
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

/**
 *
 */
@Path("user")
public class UserRessource {
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @RolesAllowed({"ADMINISTRATEUR"})
    public List<User> getusers(){
        return new UserDaoImpl().getAll();
    }
    
   
    
    @POST
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMINISTRATEUR"})
    public User create(User user){
        return new UserDaoImpl().create(user);
    }
    
    @PUT
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMINISTRATEUR"})
    public User update(User user){
        
        return new UserDaoImpl().update(user);
    }

    @DELETE
    @Path("delete/{id}")
    @RolesAllowed({"ADMINISTRATEUR"})
    public boolean delete(@PathParam("id") Long id){
        return new UserDaoImpl().delete(id);
    }

    @GET
    @Path("{id}")
    @RolesAllowed({"ADMINISTRATEUR"})
    public User getById(@PathParam("id") Long id) {
        return new UserDaoImpl().getById(id);
    }

   

}
