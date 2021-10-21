/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.UserDao;
import dbconnexion.ConnexionManager;
import domaine.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserDaoImpl implements UserDao{
    private static final String SQL_SELECT_ALL = "select * from user";
    private static final String SQL_SELECT_BY_ID = "select * from user where id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE from user where id = ?";
    private static final String SQL_INSERT = "insert into user(nom,prenom,login,password,profil) values(?,?,?,?,?)";
    private static final String SQL_UPDATE = "update user set  nom=?, prenom=?,login=?,password=?,profil=? where id=?";
    private static final String SQL_FIND_BY_USERNAME= "select * from user where login = ?";

    @Override
    public User create(User t) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        int b = -11;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getPrenom());
            preparedStatement.setString(3, t.getLogin());
            preparedStatement.setString(4, t.getPassword());
            preparedStatement.setString(5, t.getProfil());

            b = preparedStatement.executeUpdate();
            if (b > 0) {
                ResultSet r = preparedStatement.getGeneratedKeys();
                if (r.next()) {
                    t.setId(r.getLong(1));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        ConnexionManager.closeConnection(connection);
        return b > 0 ? t : null;
    }

    @Override
    public User update(User t) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        int b = -11;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getPrenom());
            preparedStatement.setString(3, t.getLogin());
            preparedStatement.setString(4, t.getPassword());
            preparedStatement.setString(5, t.getProfil());
            preparedStatement.setLong(6, t.getId());
            b = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        ConnexionManager.closeConnection(connection);
        return b > 0 ? t : null;
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        int b = -11;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            b = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        ConnexionManager.closeConnection(connection);
        return b >= 0 ? true : false;
    }

    @Override
    public List<User> getAll() {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        List<User> liste = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                getUserFromResultSet(user, resultSet);
                liste.add(user);
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        ConnexionManager.closeConnection(connection);
        return liste;
    }

    @Override
    public User getById(Long id) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        User user = new User();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                getUserFromResultSet(user, resultSet);

            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        ConnexionManager.closeConnection(connection);
        return user;
    }
    @Override
    public User findByLogin(String login) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        User user = new User();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_USERNAME);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                getUserFromResultSet(user, resultSet);

            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        ConnexionManager.closeConnection(connection);
        return user;
    }

    private void getUserFromResultSet(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getLong("id"));
        user.setNom(resultSet.getString("nom"));
        user.setPrenom(resultSet.getString("prenom"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setProfil(resultSet.getString("profil"));
    }

}
