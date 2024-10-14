package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Administrador;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Tarea;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import com.DesWebInt_2024_2.PlatGesCapHum.service.TareaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    // Mostrar formulario de creación de tarea (solo para administradores)
    @GetMapping("/crear")
    public String mostrarFormularioCrearTarea(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario instanceof Administrador) {
            model.addAttribute("tarea", new Tarea());
            return "administrador/crearTarea"; // HTML: crearTarea.html
        }
        return "redirect:/login";
    }

    // Crear una nueva tarea (solo para administradores)
    @PostMapping("/crear")
    public String crearTarea(@ModelAttribute Tarea tarea, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario instanceof Administrador) {
            tareaService.crearTarea(tarea);
            return "redirect:/tarea/crear";
        }
        return "redirect:/login";
    }
}
