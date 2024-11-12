package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Tarea;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TareaRepository tareaRepository;

    // Ruta para obtener todas las tareas en formato JSON
    @GetMapping
    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.findAll();
    }
}
