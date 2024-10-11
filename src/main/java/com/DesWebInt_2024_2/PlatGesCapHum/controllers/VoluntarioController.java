package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Voluntario;
import com.DesWebInt_2024_2.PlatGesCapHum.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {

    //INYECCION DE DEPENDENCIA
    @Autowired
    private UsuarioService usuarioService;

    // Mostrar el formulario para crear un voluntario
    @GetMapping("/registrar")
    public String mostrarFormularioCreacionVoluntario(Model model) {
        model.addAttribute("voluntario", new Voluntario()); // Agregar un nuevo objeto Voluntario
        return "voluntario/crear-voluntario"; // Nombre del archivo HTML para el formulario de voluntario
    }

    // Registrar un nuevo voluntario
    @PostMapping("/registrar")
    public String registrarVoluntario(@ModelAttribute Voluntario voluntario, Model model) {
        try {
            usuarioService.registrarUsuario(voluntario);
            model.addAttribute("mensaje", "Voluntario creado exitosamente.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/voluntario/registrar"; // Volver a la ruta GET
    }
}
