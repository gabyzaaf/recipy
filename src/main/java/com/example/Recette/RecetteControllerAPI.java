package com.example.Recette;

import com.example.Recette.Delete.RecetteDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by CLAPERT on 03/04/2016.
 */
public class RecetteControllerAPI {

    @Autowired
    private RecetteDao recetteDao;

    @RequestMapping(
            value = "/api/recette/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<String> createRecette(@RequestBody Recette recette) {
        try {
            if (recette.getTitle() == "" || recette.getTitle() == null) {
                return new ResponseEntity<String>("Error you must insert title", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (recette.getContenu() == "" || recette.getContenu() == null) {
                return new ResponseEntity<String>("The recette is not conforme", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            int nb = recetteDao.countTitle(recette.getTitle());
            if (nb != 0) {
                return new ResponseEntity<String>("The recette exist", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error exception "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(recetteDao.save(recette)==null)
            return new ResponseEntity<String>("The recette is NOT inserted ",HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<String>("The recette is insert ",HttpStatus.OK);
    }


    @RequestMapping(
            value="/api/recette/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> updateRecette(@RequestBody Recette recette)
    {

        if(recette==null){
            return new ResponseEntity<String>("The recette value is null",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Recette recet = recetteDao.findByTitle(recette.getTitle());
        if(recet==null){
            return new ResponseEntity<String>("You can't update your recette",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        recette.setId(recet.getId());
        if(recetteDao.save(recette)==null){
            return new ResponseEntity<String>("The update is not good",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Your update is perfect : Your recette are modified",HttpStatus.OK);
    }

    @RequestMapping(
            value="/api/recette/delete",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<String> deleteRecette(@RequestBody RecetteDelete RecetteDelete)
    {


        if(RecetteDelete==null){
            return new ResponseEntity<String>("Your value is empty",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Recette recette = recetteDao.findByTitle(RecetteDelete.getRecetteToDelete().getTitle());
        recetteDao.delete(recette);
        return new ResponseEntity<String>("The recipy is deleted",HttpStatus.OK);

    }



}
