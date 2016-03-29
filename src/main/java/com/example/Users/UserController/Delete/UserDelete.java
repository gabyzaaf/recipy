package com.example.Users.UserController.Delete;

import com.example.Hash.Md5;
import com.example.Users.User;

/**
 * Created by zaafranigabriel on 27/03/2016.
 */
public class UserDelete {
    private String login;
    private String pwds;
    private User userToDelete;
    public UserDelete(){

    }

    public UserDelete(String login, String pwds, User userToDelete) {
        this.login = login;
        this.pwds = pwds;
        this.userToDelete = userToDelete;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwds;
    }

    public void setPwd(String pwds) {
        if(pwds==null || pwds==""){
            this.pwds = pwds;
        }else {
            this.pwds = Md5.md5(pwds);
        }
    }


    public User getUserToDelete() {
        return userToDelete;
    }

    public void setUserToDelete(User userToDelete) {
        this.userToDelete = userToDelete;
    }

}
