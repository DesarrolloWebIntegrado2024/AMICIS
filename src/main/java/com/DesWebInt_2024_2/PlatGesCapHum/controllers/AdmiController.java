package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Administrador;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Tarea;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import com.DesWebInt_2024_2.PlatGesCapHum.service.TareaService;
import com.DesWebInt_2024_2.PlatGesCapHum.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdmiController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TareaService tareaService;

    // Mostrar formulario de registro de administrador
    @GetMapping("/registrar")
    public String mostrarFormularioCreacionAdmin(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "administrador/crear-administrador"; // HTML: crear-administrador.html
    }

    // Registrar un nuevo administrador
    @PostMapping("/registrar")
    public String registrarAdministrador(@ModelAttribute Administrador administrador, Model model) {
        try {
            usuarioService.registrarUsuario(administrador);
            model.addAttribute("mensaje", "Administrador creado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/admin/registrar";
    }

    // Mostrar todas las tareas (solo para administradores)
    @GetMapping("/tareas")
    public String verTareas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null && usuario instanceof Administrador) {
            List<Tarea> tareas = tareaService.obtenerTodasLasTareas();
            model.addAttribute("tareas", tareas);
            return "administrador/verTareas"; // HTML: verTareas.html
        }
        return "redirect:/login";
    }

    // Ver la lista de voluntarios
    @GetMapping("/voluntarios")
    public String verVoluntarios(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario instanceof Administrador) {
            List<Usuario> voluntarios = usuarioService.obtenerUsuariosPorRol("Voluntario");
            model.addAttribute("voluntarios", voluntarios);
            return "administrador/ver-voluntarios"; // HTML: ver-voluntarios.html
        }
        return "redirect:/login";
    }
}
