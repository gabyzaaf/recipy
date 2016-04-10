package com.example;

import com.example.Recette.Recette;
import com.example.Recette.RecetteDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.LinkedList;
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
        Recette recette = new Recette("cake1","content of the chocolate","imageChocolate.jpg",true,false,id);
        Assert.assertNotNull("can't be null",recette.getTitle());
        int nb = recetteDao.countTitle(recette.getTitle());
        Assert.assertEquals("must be equal 0 ",nb,0);
        Assert.assertNotNull("can't be null ",recetteDao.save(recette));
        Recette recette1 = recetteDao.save(recette);
        Assert.assertNotNull(recette1);

    }

    @Test
    public void should_find_by_title(){
        Assert.assertNotNull("the title doesn't exist",recetteDao.findByTitle("chocolates cake"));
    }


    @Test
    public void should_updating_recette(){
        Recette recette  = new Recette("vanille cake","content","imageChocolate.jpg",true,false,2);
        // checking exist Recette
        int nb = recetteDao.countTitle(recette.getTitle());
        Assert.assertEquals("Error title, the return must be equal 1",nb,1);
        // Get the exesting recette and add id
        LinkedList<Recette> liste =recetteDao.findByTitleAndId("vanille cake",2);
        recette.setId(liste.get(0).getId());
        Assert.assertNotNull("Can't be null",recetteDao.save(recette));
    }


}
