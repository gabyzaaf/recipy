package com.example.Users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zaafranigabriel on 20/03/2016.
 */
@Repository
public interface UserDao extends CrudRepository<User,Long> {

    public LinkedList<User> findByLoginsAndPwd(String logins, String pwd);
    public  User save(User user);
    @Query("SELECT count(u) FROM User u where logins=:logins AND pwd=:pwd")
    public int countUser(@Param("logins") String logins,@Param("pwd")String pwd);
    @Query("SELECT count(u) FROM User u where logins=:logins OR email=:email")
    public int countLoginsOrEmail(@Param("logins") String logins,@Param("email")String pwd);
    public User findByToken(String token);
    @Query("SELECT count(u) FROM User u where logins=:logins")
    public int countByLogins(@Param("logins")String logins);
    public User findByLogins(String logins);

}
