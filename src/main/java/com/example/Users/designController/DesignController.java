package com.example.Users.designController;

import com.example.Users.Authentification.Authentification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zaafranigabriel on 31/03/2016.
 */
@Controller
public class DesignController {

    @RequestMapping(value="/Authentification", method=RequestMethod.GET)
    public String AuthentificationForm(Model model) {
        model.addAttribute("Authentification", new Authentification());
        return "Authentification";
    }



    @RequestMapping(
            value="/Authentification",
            method = RequestMethod.POST
    )
    public String AuthentificationSubmit(@ModelAttribute Authentification authentification, Model model){
        model.addAttribute("authentification",authentification);
        return "result";
    }

}
