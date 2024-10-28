package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.*;
import com.DesWebInt_2024_2.PlatGesCapHum.service.GrupoService;
import com.DesWebInt_2024_2.PlatGesCapHum.service.TareaService;
import com.DesWebInt_2024_2.PlatGesCapHum.service.VoluntarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private VoluntarioService voluntarioService;

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
            // Crear y asociar grupo a la tarea
            Grupo grupo = new Grupo();
            grupo.setTarea(tarea);
            tarea.setGrupo(grupo);

            tareaService.crearTarea(tarea); // Guardar tarea y grupo
            return "redirect:/admin/tareas";
        }
        return "redirect:/login";
    }

    // Inscribir voluntario en un grupo
    @PostMapping("/inscribir")
    public String inscribirVoluntario(@RequestParam Long grupoId, HttpSession session) {
        Voluntario voluntario = (Voluntario) session.getAttribute("usuario");
        if (voluntario == null) {
            return "redirect:/login";
        }

        boolean inscrito = grupoService.inscribirVoluntarioAGrupo(grupoId, voluntario);
        if (inscrito) {
            return "redirect:/voluntario/tareas";
        } else {
            return "redirect:/voluntario/tareas?error=cupo";
        }
    }

    // Mostrar formulario de edición de tarea
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarTarea(@PathVariable Long id, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario instanceof Administrador) {
            Tarea tarea = tareaService.findById(id).orElse(null);
            if (tarea != null) {
                model.addAttribute("tarea", tarea);

                Grupo grupo = tarea.getGrupo();
                if (grupo != null) {
                    Set<Voluntario> voluntarios = grupo.getVoluntarios();
                    model.addAttribute("voluntarios", voluntarios);

                    Voluntario lider = grupo.obtenerLider();
                    model.addAttribute("liderActual", lider);
                }
                return "administrador/editarTarea";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/actualizar")
    public String actualizarTarea(@ModelAttribute Tarea tarea, @RequestParam Long tareaId,
                                  @RequestParam(required = false) Long liderId, HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario instanceof Administrador) {
            Tarea tareaExistente = tareaService.findById(tareaId).orElse(null);
            if (tareaExistente != null) {
                actualizarDetallesTarea(tarea, tareaExistente);

                Grupo grupo = tareaExistente.getGrupo();
                if (grupo != null) {
                    // Actualizar el número de voluntarios inscritos
                    int cantidadVoluntarios = grupo.getVoluntarios().size();
                    tareaExistente.setCantidadVoluntariosInscritos(cantidadVoluntarios);

                    // Manejo del líder
                    Voluntario liderActual = grupo.obtenerLider();  // Obtener el líder actual
                    if (liderActual != null) {
                        grupoService.desasignarLider(grupo);  // Desasignar al líder actual
                    }

                    if (liderId != null) {
                        Voluntario nuevoLider = voluntarioService.findById(liderId).orElse(null);
                        if (nuevoLider != null) {
                            grupoService.asignarLiderAGrupo(grupo, nuevoLider);  // Asignar al nuevo líder
                        }
                    }
                }

                tareaService.actualizarTarea(tareaExistente);
                return "redirect:/admin/tareas";
            } else {
                model.addAttribute("error", "Tarea no encontrada.");
                return "redirect:/tarea/editar/" + tareaId;
            }
        }
        return "redirect:/login";
    }

    private void actualizarDetallesTarea(Tarea tarea, Tarea tareaExistente) {
        tareaExistente.setNombre(tarea.getNombre());
        tareaExistente.setDescripcion(tarea.getDescripcion());
        tareaExistente.setFechaInicio(tarea.getFechaInicio());
        tareaExistente.setFechaFin(tarea.getFechaFin());
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        // Buscar la tarea por ID
        Tarea tarea = tareaService.obtenerTareaPorId(id);
        if (tarea == null) {
            // Manejo de error si la tarea no existe
            return "redirect:/admin/tareas?error=no_encontrada";
        }

        // Verificar que la tarea esté en estado PENDIENTE
        if (tarea.getEstadoTarea() == Tarea.EstadoTarea.PENDIENTE) {
            // Llamar al método eliminarTarea en TareaService
            tareaService.eliminarTarea(id);
            return "redirect:/admin/tareas";
        }

        // Manejo de error si la tarea no puede ser eliminada
        return "redirect:/admin/tareas?error=no_eliminable";
    }
}
