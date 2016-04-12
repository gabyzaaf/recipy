package com.example.Users;

import com.example.Hash.Md5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zaafranigabriel on 20/03/2016.
 */
@Entity
@Table(name="Utilisateur")
public class User {

    @Id
    private long id;
    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="admin")
    private boolean admin;
    @Column(name="actif")
    private boolean actif;
    @Column(name="logins")
    private String logins;
    @Column(name="email")
    private String email;
    @Column(name="naissance")
    private String naissance;
    @Column(name="pwd")
    private String pwd;
    @Column(name="compte")
    private String compte;
    @Column(name="Token")
    private String token;

    public User(){

    }

    public User(String nom, String prenom, boolean admin, boolean actif, String logins, String email, String naissance, String pwd, String compte) {
        this.nom = nom;
        this.prenom = prenom;
        this.admin = admin;
        this.actif = actif;
        this.logins = logins;
        this.email = email;
        this.naissance = naissance;
        this.pwd = pwd;
        this.compte = compte;
        if(this.pwd!=null || this.pwd!=""){
            this.pwd = Md5.md5(this.pwd);
        }


    }

    public User(String nom, String prenom, boolean admin, boolean actif, String logins, String email, String naissance, String pwd, String compte,String Token) {
        this.nom = nom;
        this.prenom = prenom;
        this.admin = admin;
        this.actif = actif;
        this.logins = logins;
        this.email = email;
        this.naissance = naissance;
        this.pwd = pwd;
        this.compte = compte;
        if(this.pwd!=null || this.pwd!=""){
            this.pwd = Md5.md5(this.pwd);
        }
        this.token = Token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getLogins() {
        return logins;
    }

    public void setLogins(String logins) {
        this.logins = logins;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public String getPwd() {
        return pwd;
    }
    //test this function
    public void setPwd(String pwd) {

        if(pwd!=null || pwd!=""){
            this.pwd = Md5.md5(pwd);
        }else {
            this.pwd = pwd;
        }

    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", admin=" + admin +
                ", actif=" + actif +
                ", logins='" + logins + '\'' +
                ", email='" + email + '\'' +
                ", naissance='" + naissance + '\'' +
                ", pwd='" + pwd + '\'' +
                ", compte='" + compte + '\'' +
                '}';
    }

}
