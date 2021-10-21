/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
public class Article {
    private Long id;
    private String titre;
    private String contenu;
    private Date dateCreation;
    private Date dateModification;
    private Long categorie;

    public Article() {
    }

    public Article(Long id, String titre, String contenu, Date dateCreation, Date dateModification, Long categorie) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.categorie = categorie;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date date_creation) {
        this.dateCreation = date_creation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date date_modification) {
        this.dateModification = date_modification;
    }

    public Long getCategorie() {
        return categorie;
    }

    public void setCategorie(Long categorie) {
        this.categorie = categorie;
    }
    
    
    
}
