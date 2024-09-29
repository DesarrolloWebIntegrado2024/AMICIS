package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestimoniosController {

    @GetMapping("/testimonios")
    public ModelAndView showMyView() {
        ModelAndView mav = new ModelAndView("layout"); // Nombre del layout JSP
        mav.addObject("pageTitle", "Página Principal"); // Título dinámico de la página
        mav.addObject("pageContent", "testimonios.jsp"); // Vista que contiene el contenido
        return mav;
    }
}
