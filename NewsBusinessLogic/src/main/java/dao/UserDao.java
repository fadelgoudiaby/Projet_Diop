/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domaine.User;

/**
 *
 * @author pbfall
 */
public interface UserDao extends DaoCrud<User>{
    public User findByLogin(String login);
}
