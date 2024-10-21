package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.*;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {

    //INYECCION DE DEPENDENCIA
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TareaService tareaService;

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


    @GetMapping("/tareas")
    public String verTareas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login"; // Redirigir si no hay usuario en sesión
        }
        if (usuario instanceof Voluntario) {
            Voluntario voluntario = (Voluntario) usuario;

            List<Tarea> todasLasTareas = tareaService.obtenerTodasLasTareas();
            List<Tarea> tareasInscritas = new ArrayList<>();

            for (Tarea tarea : todasLasTareas) {
                // Verificar si el voluntario está inscrito en el grupo de la tarea
                if (tarea.getGrupo() != null && verificarVoluntarioEnGrupo(voluntario, tarea.getGrupo())) {
                    tareasInscritas.add(tarea);
                }
            }
            model.addAttribute("tareas", todasLasTareas);
            model.addAttribute("tareasInscritas", tareasInscritas);
            return "voluntario/verTareas";
        }
        return "redirect:/login";
    }

    // Método que verifica si el voluntario está inscrito en el grupo
    private boolean verificarVoluntarioEnGrupo(Voluntario voluntario, Grupo grupo) {
        return grupo != null && grupo.getVoluntarios().stream()
                .anyMatch(v -> v.getIdUsuario().equals(voluntario.getIdUsuario()));
    }

    @GetMapping("/mis-tareas")
    public String verMisTareas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login"; // Redirigir si no hay usuario en sesión
        }
        if (usuario instanceof Voluntario) {
            Voluntario voluntario = (Voluntario) usuario;
            List<Tarea> tareasInscritas = new ArrayList<>();

            // Obtener todas las tareas y filtrar solo las que el voluntario está inscrito
            List<Tarea> todasLasTareas = tareaService.obtenerTodasLasTareas();
            for (Tarea tarea : todasLasTareas) {
                if (tarea.getGrupo() != null && verificarVoluntarioEnGrupo(voluntario, tarea.getGrupo())) {
                    tareasInscritas.add(tarea);
                }
            }
            model.addAttribute("tareasInscritas", tareasInscritas);
            return "voluntario/misTareas"; // Nombre de la vista de "Mis Tareas"
        }
        return "redirect:/login";
    }

}
