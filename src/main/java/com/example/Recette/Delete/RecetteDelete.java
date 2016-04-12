package com.example.Recette.Delete;

import com.example.Recette.Recette;

/**
 * Created by CLAPERT on 03/04/2016.
 */
public class RecetteDelete {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public RecetteDelete(){

    }

    public RecetteDelete( String title)
    {
        this.title = title;

    }

    public String getTitle() {
        return title;
    }


}
