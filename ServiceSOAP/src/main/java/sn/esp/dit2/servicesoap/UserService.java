/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.esp.dit2.servicesoap;

import dao.impl.UserDaoImpl;
import domaine.User;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 */

@WebService
public class UserService {
    ArrayList<User> usersConnected = new ArrayList<User>();
    @WebMethod
    public boolean authentication(@WebParam(name="login")String login, @WebParam(name="password") String password){
        User user = new UserDaoImpl().findByLogin(login);
        if(user!=null){
            if(user.getPassword().equals(password) && user.getProfil().equals("ADMINISTRATEUR")){
                if(!usersConnected.contains(user)){
                    usersConnected.add(user);
                }
                return true;
            }
        }
        return false;
    }
    
    @WebMethod
    public String create(User user,@WebParam(name="login") String login ,@WebParam(name="password") String password){
        if(authentication(login, password)){
            User u = new UserDaoImpl().create(user);
            return u==null?"erreur creation":"creation reussie";
        }else{
            return "Veuillez-vous connecter";
        }
    }
    
    @WebMethod
    public String update(User user,@WebParam(name="login") String login,@WebParam(name="password")String password){
        if(authentication(login,password)){
            User u = new UserDaoImpl().update(user);
            return "user mis à jour.";
        }else{
            return "Veuillez-vous connecter";
        }
    }
    
    @WebMethod 
    public String delete(@WebParam(name="id") Long id,@WebParam(name="login") String login,@WebParam(name="password")String password){
       if(authentication(login,password)){
            boolean u = new UserDaoImpl().delete(id);
            return u?"user supprimé.":"echec suppression";
        }else{
            return "Veuillez-vous connecter";
        }
      
    }
    
    @WebMethod
    public List<User> getAll(){
        return new UserDaoImpl().getAll();
    }
    
    @WebMethod
    public String deconnexion(@WebParam(name="login")String login, @WebParam(name="password") String password){
        User user = new UserDaoImpl().findByLogin(login);
        if(user!=null){
            if(user.getPassword().equals(password)){
                if(usersConnected.contains(user)){
                    usersConnected.remove(user);
                    return "deconnexion reussie...";
                }
                
            }
        }
        return "Impossible de traiter votre demande.";
    }
}
