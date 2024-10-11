package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Administrador;
import com.DesWebInt_2024_2.PlatGesCapHum.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdmiController {

    //INYECCION DE DEPENDENCIA
    @Autowired
    private UsuarioService usuarioService;


    // Mostrar el formulario para crear un administrador
    @GetMapping("/registrar")
    public String mostrarFormularioCreacionAdmin(Model model) {
        model.addAttribute("administrador", new Administrador()); // Agregar un nuevo objeto Administrador
        return "administrador/crear-administrador"; // Nombre del archivo HTML para el formulario de administrador
    }

    // Registrar un nuevo administrador
    @PostMapping("/registrar")
    public String registrarAdministrador(@ModelAttribute Administrador administrador, Model model) {
        try {
            usuarioService.registrarUsuario(administrador);
            model.addAttribute("mensaje", "Administrador creado exitosamente.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/registrar"; // Volver a la ruta GET
    }

}
