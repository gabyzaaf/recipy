package com.example;
import com.example.Hash.Md5;
import  com.example.Users.*;
import com.example.Users.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zaafranigabriel on 20/03/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RecipyProjectApplication.class)
@WebAppConfiguration
public class UserTest {

    @Autowired
    UserDao userDao;
    @Test
    public void should_create_user(){
        User user = new User("zaafrani","gabriel",true,false,"gzaa","gabriel.zaafrani@gmail.com","1990/09/29","test","autre");
        Assert.assertEquals("equals",user.getNom(),"zaafrani");
    }

    @Test
    public void should_connected_by_login_and_pwd(){
        String val=Md5.md5("levy");
        int nbuser = userDao.countUser("cohen",val);
        Assert.assertEquals("Must be equal 1",nbuser,1);
        // we get the user for update the token
        LinkedList<User> listUser =userDao.findByLoginsAndPwd("cohen",val);
        if(listUser.get(0).getToken()=="" || listUser.get(0).getToken()==null){
            listUser.get(0).setToken(Md5.md5(listUser.get(0).getId()+listUser.get(0).getLogins()));
        }
        Assert.assertNotNull(userDao.save(listUser.get(0)));

    }

    @Test
    public void should_disconnect_Logins_and_password(){
     // checking User
        String val=Md5.md5("levy");
        int nbuser = userDao.countUser("cohen",val);
        Assert.assertEquals("Must be equal 1",nbuser,1);
    //  Getting user and cheking if Token exist
        LinkedList<User> userlist=userDao.findByLoginsAndPwd("cohen",val);
        Assert.assertNotNull("the token can't be null ",userlist.get(0).getToken());
        userlist.get(0).setToken(null);
        Assert.assertNotNull(userDao.save(userlist.get(0)));
    }

    @Test
    public void should_insert_in_the_database(){
        User user = new User("Zaafrani","David",true,false,"cohen","salomon.zaafrani@gmail.com","1990/09/29","david","autre");
        int nb = userDao.countUser(user.getLogins(),user.getPwd());
        Assert.assertEquals("must be equal 0 ",nb,0);
        User user1 = userDao.save(user);
        Assert.assertNotNull(user1);
    }


    @Test
    public void should_delete_user(){
        String val=Md5.md5("david");
        int nb = userDao.countUser("cohen",val);
        Assert.assertEquals("must be equal 1",nb,1);
        LinkedList<User> user = userDao.findByLoginsAndPwd("cohen",val);
        userDao.delete(user.get(0).getId());
    }

   @Test
    public void should_updating_user(){
       User user  = new User("Levy","David",true,false,"cohen","levy.zaafrani@gmail.com","1990/09/29","levy","autre");
       // checking exist User
       String val =  Md5.md5("david");
       int nb = userDao.countUser(user.getLogins(),val);
       Assert.assertEquals("Must be equal 1",nb,1);
        // Get the exesting user and add id and token
        LinkedList<User> liste =userDao.findByLoginsAndPwd("cohen",val);
        user.setId(liste.get(0).getId());
        user.setToken(liste.get(0).getToken());
        Assert.assertNotNull("Can't be null",userDao.save(user));
   }
}
