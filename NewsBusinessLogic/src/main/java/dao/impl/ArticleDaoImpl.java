/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.ArticleDao;
import dbconnexion.ConnexionManager;
import domaine.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class ArticleDaoImpl implements ArticleDao{
    private static final String SQL_SELECT_ALL = "select * from article order by dateCreation desc";
    private static final String SQL_SELECT_BY_CATEGORIE = "select * from article where categorie=? order by dateCreation desc";
    private static final String SQL_SELECT_GROUPBY_CATEGORIE = "select * from article group by categorie order by dateCreation desc";

    private static final String SQL_SELECT_BY_ID = "select * from article where id = ?";
    private static final String SQL_DELETE_BY_ID = "delete from article where id = ?";
    private static final String SQL_INSERT = "insert into article(titre,contenu,dateCreation,dateModification) values(?,?,now(),now())";
    private static final String SQL_UPDATE = "update article set titre=?, contenu=?,categorie=?, dateModification=now() where id=?";
    @Override
    public List<Article> getListByCategorie(Long categorie) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        List<Article> liste = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CATEGORIE);
            preparedStatement.setLong(1, categorie);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Article ar = new Article();
                ar = getArticleFromResultSet(ar, resultSet);
                liste.add(ar);
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
    public List<Article> getListGroupeByCategorie() {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        List<Article> liste = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_GROUPBY_CATEGORIE);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Article ar = new Article();
                ar = getArticleFromResultSet(ar, resultSet);
                liste.add(ar);
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
    public Article create(Article t) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        int b = -11;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, t.getTitre());
            preparedStatement.setString(2, t.getContenu());
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
    public Article update(Article t) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        int b = -11;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, t.getTitre());
            preparedStatement.setString(2, t.getContenu());
            preparedStatement.setLong(3, t.getCategorie());
            preparedStatement.setLong(4, t.getId());
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
    public List<Article> getAll() {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        List<Article> liste = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Article ar = new Article();
                ar = getArticleFromResultSet(ar, resultSet);
                liste.add(ar);
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
    public Article getById(Long id) {
        Connection connection = ConnexionManager.getConnection();
        PreparedStatement preparedStatement = null;
        Article ar = new Article();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                getArticleFromResultSet(ar, resultSet);

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
        return ar;
    }

    public Article getArticleFromResultSet(Article ar, ResultSet resultSet) throws SQLException {
        ar.setId(resultSet.getLong("id"));
        ar.setTitre(resultSet.getString("titre"));
        ar.setContenu(resultSet.getString("contenu"));
        ar.setCategorie(resultSet.getLong("categorie"));
        ar.setDateCreation(new Date(resultSet.getTimestamp("dateCreation").getTime()));
        ar.setDateModification(new Date(resultSet.getTimestamp("dateModification").getTime()));
        return ar;
    }
    
}
