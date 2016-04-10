package com.example.Recette.Delete;

import com.example.Recette.Recette;

/**
 * Created by CLAPERT on 03/04/2016.
 */
public class RecetteDelete {
    private String title;
    private Recette RecetteToDelete;

    public RecetteDelete(){

    }

    public RecetteDelete(Recette RecetteToDelete, String title)
    {
        this.RecetteToDelete = RecetteToDelete;
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public Recette getRecetteToDelete() {
        return RecetteToDelete;
    }
}
