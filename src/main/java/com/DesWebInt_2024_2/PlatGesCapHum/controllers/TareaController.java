package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.exception.ResourceNotFoundException;
import com.DesWebInt_2024_2.PlatGesCapHum.model.*;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.LiderRepository;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.VoluntarioRepository;
import com.DesWebInt_2024_2.PlatGesCapHum.service.TareaService;
import com.DesWebInt_2024_2.PlatGesCapHum.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VoluntarioRepository voluntarioRepository;
    @Autowired
    private LiderRepository liderRepository;

    // Mostrar todas las Tareas (sera lo primero q el Admi vera cuando inicie sesion)
    @GetMapping()
    public String mostrarTareas(Model model) {
        List<Tarea> tareas = tareaService.obtenerTodasLasTareas(); // Obtener todas las tareas
        for (Tarea tarea : tareas) {
            System.out.println("ID: " + tarea.getId() + ", Estado: " + tarea.getEstadoTarea());
        }
        model.addAttribute("tareas", tareas); // Agregar las tareas al modelo
        return "administrador/admi"; // Nombre del archivo HTML para mostrar las tareas
    }


    // Mostrar el formulario para crear una Tarea
    @GetMapping("/crear")
    public String mostrarFormularioCrearTarea(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "administrador/crearTarea"; // Nombre del archivo HTML para crear Tarea
    }

    // Crear una nueva tarea
    @PostMapping
    public String crearTarea(@ModelAttribute Tarea tarea, Model model) {
        try {
            tareaService.crearTarea(tarea); // Llama al servicio para crear la tarea
            System.out.println("Estado tarea:"+tarea.getEstadoTarea());
            model.addAttribute("mensaje", "Tarea creada exitosamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear la tarea: " + e.getMessage());
        }
        return "redirect:/tarea/crear"; // Redirige a la página de creación de tareas
    }
}


