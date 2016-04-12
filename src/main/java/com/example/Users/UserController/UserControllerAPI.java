package com.example.Users.UserController;

import com.example.Hash.Md5;
import com.example.Users.Authentification.Authentification;
import com.example.Users.UserController.Delete.UserDelete;
import com.example.Users.User;
import com.example.Users.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

/**
 * Created by zaafranigabriel on 24/03/2016.
 */
@RestController
public class UserControllerAPI {

    @Autowired
    private UserDao userDao;

    @CrossOrigin
    @RequestMapping(
            value="/api/user/connection",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody ResponseEntity<User> connect(@RequestBody Authentification authentification){
        try {
            if (authentification.getLogins() == "" || authentification.getLogins() == null) {
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String val = Md5.md5(authentification.getPwd());
            int nbuser = userDao.countUser(authentification.getLogins(), val);
            // we get the user for update the token
            LinkedList<User> listUser = userDao.findByLoginsAndPwd(authentification.getLogins(), val);
            if (listUser.get(0).getToken() == "" || listUser.get(0).getToken() == null) {
                listUser.get(0).setToken(Md5.md5(listUser.get(0).getId() + listUser.get(0).getLogins()));
            }
            if (userDao.save(listUser.get(0)) == null) {
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<User>(listUser.get(0),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            value="/api/user/disconnect",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> disconnect(@RequestBody Authentification authentification)
    {
        // checking User
        String val=Md5.md5(authentification.getPwd());
        int nbuser = userDao.countUser(authentification.getLogins(),val);
        if(nbuser==0){
            return new ResponseEntity<String>("Error because the Login or password is failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //  Getting user and cheking if Token exist
        LinkedList<User> userlist=userDao.findByLoginsAndPwd(authentification.getLogins(),val);
        if(userlist.get(0).getToken()==null){
            return new ResponseEntity<String>("You are not connecting",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userlist.get(0).setToken(null);
        if(userDao.save(userlist.get(0))==null){
            return new ResponseEntity<String>("Error because in the User updating ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("You are disconnect",HttpStatus.OK);
    }


    @RequestMapping(
            value="/api/user/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> createUser(@RequestBody User user)
    {

        try {
            if (user.getLogins() == "" || user.getLogins() == null) {
                return new ResponseEntity<String>("Error you must insert login", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (user.getEmail() == "" || user.getEmail() == null || user.getEmail().contains("@") == false) {
                return new ResponseEntity<String>("The email is not conforme", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (user.getNom() == "" || user.getNom() == null || user.getPrenom() == "" || user.getPrenom() == null || user.getPwd() == "" || user.getPwd() == null) {
                return new ResponseEntity<String>("Error the name,firstname and pwd can't be empty", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            int nb = userDao.countLoginsOrEmail(user.getLogins(), user.getEmail());
            if (nb != 0) {
                return new ResponseEntity<String>("The user exist", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error exception "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userDao.save(user)==null)
            return new ResponseEntity<String>("The user is NOT inserted ",HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<String>("The user is insert ",HttpStatus.OK);

    }

    @RequestMapping(
            value="/api/user/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> updateUser(@RequestBody  User user)
    {

        if(user==null){
            return new ResponseEntity<String>("The user value is null",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(user.getToken()=="" ||user.getToken()==null){
            return new ResponseEntity<String>("You are not connected ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User use = userDao.findByToken(user.getToken());
        if(use==null){
            return new ResponseEntity<String>("Your token don't exist you are not the user",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(use.getLogins()!=user.getLogins()){
            return new ResponseEntity<String>("You can't update you login",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        

        user.setId(use.getId());
        if(userDao.save(user)==null){
            return new ResponseEntity<String>("The update is not good",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Your update is perfect : Your user are modified",HttpStatus.OK);
    }

    @RequestMapping(
            value="/api/user/delete",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> deleteUser(@RequestBody UserDelete userDelete)
    {


        if(userDelete==null){
            return new ResponseEntity<String>("Your value is empty",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userDelete.getLogin()=="" || userDelete.getLogin()==null || userDelete.getPwd()=="" || userDelete.getPwd()==null)
        {
            return new ResponseEntity<String>("You don't insert your login or you password",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LinkedList<User> userList = userDao.findByLoginsAndPwd(userDelete.getLogin(),userDelete.getPwd());
        if(userList.size()==0){
            return new ResponseEntity<String>("Your login or password is not correct",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userList.get(0).isAdmin()==false){
            return new ResponseEntity<String>("You can't delete user because you are not admin",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Finalyse the user delete
        int nb = userDao.countByLogins(userDelete.getUserToDelete().getLogins());

        if(nb==0){
            return new ResponseEntity<String>("The user don't exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user = userDao.findByLogins(userDelete.getUserToDelete().getLogins());
        userDao.delete(user);
        return new ResponseEntity<String>("The user is deleted",HttpStatus.OK);

    }



}
