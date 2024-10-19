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
        Voluntario voluntario = (Voluntario) session.getAttribute("voluntario");
        Grupo grupo = grupoService.obtenerGrupoPorId(grupoId); // Método para obtener el grupo

        if (grupo != null && grupo.inscribirVoluntario(voluntario)) {
            // Guardar cambios en la base de datos
            grupoService.guardarGrupo(grupo);
            return "redirect:/voluntario/tareas"; // Redirigir a tareas
        }

        return "redirect:/voluntario/tareas"; // Redirigir en caso de error
    }

}
