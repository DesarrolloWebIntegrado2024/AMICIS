package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.*;
import com.DesWebInt_2024_2.PlatGesCapHum.service.GrupoService;
import com.DesWebInt_2024_2.PlatGesCapHum.service.TareaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;
    @Autowired
    private GrupoService grupoService;

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

    @PostMapping("/crear")
    public String crearTarea(@ModelAttribute Tarea tarea, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario instanceof Administrador) {
            Grupo grupo = tarea.crearGrupo(); // Crear un grupo al crear la tarea
            tarea.getGrupos().add(grupo); // Agregar el grupo a la tarea
            tareaService.crearTarea(tarea); // Guarda la tarea con el grupo
            return "redirect:/tarea/crear"; // Redirigir a la lista de tareas
        }
        return "redirect:/login"; // Redirigir si no es un administrador
    }

    @PostMapping("/inscribir")
    public String inscribirVoluntario(@RequestParam Long grupoId, HttpSession session) {
        // Obtener el voluntario desde la sesión
        Voluntario voluntario = (Voluntario) session.getAttribute("usuario");

        if (voluntario == null) {
            // Redirigir si no hay un voluntario en sesión (no autenticado)
            return "redirect:/login";
        }
        // Obtener el grupo usando el ID
        Grupo grupo = grupoService.obtenerGrupoPorId(grupoId);

        if (grupo != null) {
            // Intentar inscribir al voluntario
            boolean inscrito = grupo.inscribirVoluntario(voluntario);
            if (inscrito) {
                // Si la inscripción es exitosa, guardar el grupo actualizado
                grupoService.guardarGrupo(grupo);
                return "redirect:/voluntario/tareas"; // Redirigir a la lista de tareas
            } else {
                // Si la inscripción falla (por ejemplo, si ya está inscrito o no hay cupo)
                return "redirect:/voluntario/tareas?error=cupo";
            }
        }
        // Redirigir si no se encuentra el grupo o hay un error
        return "redirect:/voluntario/tareas?error=grupo";
    }
}
