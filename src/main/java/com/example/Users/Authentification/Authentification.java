package com.example.Users.Authentification;

/**
 * Created by zaafranigabriel on 24/03/2016.
 */
public class Authentification {

    private String logins;
    private String pwd;

    public Authentification(){

    }

    public Authentification(String logins, String pwd) {
        this.logins = logins;
        this.pwd = pwd;
    }


    public String getLogins() {
        return logins;
    }

    public void setLogins(String logins) {
        this.logins = logins;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
