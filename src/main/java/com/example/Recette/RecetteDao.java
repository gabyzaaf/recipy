package com.example.Recette;

import com.example.Users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zaafranigabriel on 22/03/2016.
 */
public interface RecetteDao extends CrudRepository<Recette,Long> {
    public LinkedList<Recette> findByTitleAndId(String title, long id);
    public Recette save(Recette recette);
    @Query("SELECT count(r) FROM Recette r where title=:title")
    public int countTitle(@Param("title") String title);
    public Recette findByTitle(String title);
}
