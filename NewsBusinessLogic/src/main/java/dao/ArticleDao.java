/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domaine.Article;
import java.util.List;

/**
 *
 */
public interface ArticleDao extends DaoCrud<Article> {
    public List<Article> getListByCategorie(Long categorie);
    public List<Article> getListGroupeByCategorie();
}
