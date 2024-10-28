package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class paginasEstaticasController {

    @GetMapping({"/","/home"})
    public String mostrarPrincipal(Model model) {
        // Puedes agregar atributos al modelo si es necesario
        return "principal"; // Devuelve el nombre de la vista (nosotros.html)
    }

    @GetMapping("/nosotros")
    public String mostrarNosotros(Model model) {
        // Puedes agregar atributos al modelo si es necesario
        return "nosotros"; // Devuelve el nombre de la vista (nosotros.html)
    }

    @GetMapping("/proyectos")
    public String mostrarProyectos(Model model) {
        // Puedes agregar atributos al modelo si es necesario
        return "proyectos"; // Devuelve el nombre de la vista (proyectos.html)
    }

    @GetMapping("/testimonios")
    public String mostrarTestimonios(Model model) {
        // Puedes agregar atributos al modelo si es necesario
        return "testimonios"; // Devuelve el nombre de la vista (testimonios.html)
    }

}
