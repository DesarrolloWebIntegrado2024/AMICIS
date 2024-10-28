package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Grupo;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Tarea;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Voluntario;
import com.DesWebInt_2024_2.PlatGesCapHum.service.GrupoService;
import com.DesWebInt_2024_2.PlatGesCapHum.service.TareaService;
import com.DesWebInt_2024_2.PlatGesCapHum.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {

    //INYECCION DE DEPENDENCIA
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TareaService tareaService;
    @Autowired
    private GrupoService grupoService;

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
            // Añade el usuario al modelo para evitar que sea null
            model.addAttribute("usuario", usuario);
            model.addAttribute("tareasInscritas", tareasInscritas);
            return "voluntario/misTareas";
        }
        return "redirect:/login";
    }

    @PostMapping("/culminar")
    public String culminarTarea(@RequestParam Long tareaId, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario instanceof Voluntario) {
            Voluntario voluntario = (Voluntario) usuario;
            Optional<Tarea> tareaOpt = tareaService.findById(tareaId);

            if (tareaOpt.isPresent()) {
                Tarea tarea = tareaOpt.get();
                Grupo grupo = tarea.getGrupo();

                // Verificar que el voluntario sea el líder de la tarea
                if (grupo != null && grupo.obtenerLider().equals(voluntario)) {
                    tarea.setEstadoTarea(Tarea.EstadoTarea.COMPLETA);
                    tareaService.actualizarTarea(tarea);
                    model.addAttribute("mensaje", "Tarea completada exitosamente.");
                } else {
                    model.addAttribute("error", "No tienes permisos para culminar esta tarea.");
                }
            } else {
                model.addAttribute("error", "Tarea no encontrada.");
            }
        }
        return "redirect:/voluntario/mis-tareas";
    }

    @PostMapping("/retirarse")
    @Transactional  // Asegura una transacción para todo el proceso
    public String desinscribirVoluntario(@RequestParam Long grupoId, HttpSession session) {
        Voluntario voluntario = (Voluntario) session.getAttribute("usuario");
        if (voluntario == null) {
            return "redirect:/login";
        }

        // Llamar al servicio para desinscribir
        boolean desinscrito = grupoService.desinscribirVoluntario(voluntario.getIdUsuario(), grupoId);
        if (desinscrito) {
            return "redirect:/voluntario/mis-tareas"; // Redirigir con mensaje de éxito
        } else {
            return "redirect:/voluntario/tareas?error=no_encontrado"; // Redirigir con mensaje de error
        }
    }
}
