package com.example.Recette;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zaafranigabriel on 20/03/2016.
 */
@Entity
@Table(name="Recette")
public class Recette {
    @Id
    private long id;
    @Column(name="title")
    private String title;
    @Column(name="contenu")
    private String contenu;
    @Column(name="image_lien")
    private String image_lien;
    @Column(name="visible")
    private Boolean visible;
    @Column(name="partage")
    private Boolean partage;
    @Column(name="fid")
    private long fid;


    public Recette(){

    }



    public Recette(long id, String title, String contenu, String image_lien, Boolean visible, Boolean partage, long fid) {
        this.id = id;
        this.title = title;
        this.contenu = contenu;
        this.image_lien = image_lien;
        this.visible = visible;
        this.partage = partage;
        this.fid = fid;
    }
    public Recette( String title, String contenu, String image_lien, Boolean visible, Boolean partage, long fid) {

        this.title = title;
        this.contenu = contenu;
        this.image_lien = image_lien;
        this.visible = visible;
        this.partage = partage;
        this.fid = fid;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage_lien() {
        return image_lien;
    }

    public void setImage_lien(String image_lien) {
        this.image_lien = image_lien;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    public Boolean getPartage() {
        return partage;
    }

    public void setPartage(Boolean partage) {
        this.partage = partage;
    }
}
