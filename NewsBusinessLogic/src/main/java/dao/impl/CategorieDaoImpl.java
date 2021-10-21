/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.CategorieDAO;
import dbconnexion.ConnexionManager;
import domaine.Categorie;
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
public class CategorieDaoImpl implements CategorieDAO {

    private static final String SQL_SELECT_ALL = "select * from categorie";
    private static final String SQL_SELECT_BY_ID = "select * from categorie where id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE from categorie where id = ?";
    private static final String SQL_INSERT = "insert into categorie(libelle) values(?)";
    private static final String SQL_UPDATE = "update  categorie set libelle=? where id=?";

    @Override
    public Categorie create(Categorie t) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        int b = -11;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, t.getLibelle());
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
    public Categorie update(Categorie t) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        int b = -11;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, t.getLibelle());
            preparedStatement.setLong(2, t.getId());
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
    public List<Categorie> getAll() {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        List<Categorie> liste = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Categorie cat = new Categorie();
                cat.setId(resultSet.getLong("id"));
                cat.setLibelle(resultSet.getString("libelle"));
                liste.add(cat);
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
    public Categorie getById(Long id) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        Categorie cat = new Categorie();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cat.setId(resultSet.getLong("id"));
                cat.setLibelle(resultSet.getString("libelle"));

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
        return cat;
    }

}
