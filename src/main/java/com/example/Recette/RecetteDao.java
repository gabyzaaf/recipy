package com.example.Recette;

import com.example.Users.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zaafranigabriel on 22/03/2016.
 */
public interface RecetteDao extends CrudRepository<Recette,Long> {
    public Recette findByTitle(String title);
    public List<Recette> findByFid(Long id);
}
