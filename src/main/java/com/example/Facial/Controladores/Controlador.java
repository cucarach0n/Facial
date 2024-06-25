package com.example.Facial.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {
    
@GetMapping("/")
    public String inicio(Model model) {
        return "login";
    }


@GetMapping("/sistema")
    public String inicio1(Model model) {
        return "sistema";
    }

    @GetMapping("/listaPersona")
    public String inicio2(Model model) {
        return "listaPersona";
    }


}
