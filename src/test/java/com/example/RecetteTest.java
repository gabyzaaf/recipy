package com.example;

import com.example.Recette.Recette;
import com.example.Recette.RecetteDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by zaafranigabriel on 22/03/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RecipyProjectApplication.class)
@WebAppConfiguration
public class RecetteTest {

    @Autowired
    RecetteDao recetteDao;




    @Test
    public void should_add_recette(){
        long id=2;
        Recette recette = new Recette("chocolate cake","content of the chocolate","imageChocolate.jpg",true,false,id);
        Assert.assertNotNull("can't be null ",recetteDao.save(recette));

    }

    @Test
    public void should_find_by_title(){
        Assert.assertNotNull(recetteDao.findByTitle("chocolate cake"));
    }

    @Test
    public void should_find_by_user(){
        long id = 2;
        List<Recette> liste=recetteDao.findByFid(id);
        Assert.assertEquals("cant be 0",liste.size(),1);
    }



}
