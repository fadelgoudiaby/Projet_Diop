/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.esp.dit2.servicerest;

import config.Credentials;
import dao.impl.UserDaoImpl;
import domaine.User;
import java.util.Base64;
import javax.annotation.security.PermitAll;
import javax.naming.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("authentication")
public class AuthenticationRessource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response authenticateUser(Credentials credentials) {

        try {

            // Authenticate the user using the credentials provided
            authenticate(credentials.getUsername(), credentials.getPassword());

            // Issue a token for the user
            Token token = new Token(issueToken(credentials.getUsername(),credentials.getPassword()));

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }      
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        User user = new UserDaoImpl().findByLogin(username);
        // le pwd doit etre crypter (save user & test pwd).
        System.out.println("sn.esp.dit2.servicerest.AuthenticationRessource.authenticate()");
        if(user==null || !(username.equals(user.getLogin())&& password.equals(user.getPassword()))){
            throw new AuthenticationException();
        }
    }

    private String issueToken(String username,String password) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        return Base64.getEncoder().encodeToString(new String(username+":"+password).getBytes());
    }
    
    private class Token{
        String token;

        Token(String token){
            this.token = token;
        }
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
        
    }
}
    
