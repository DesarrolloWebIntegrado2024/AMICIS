package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    /*@PostMapping("/crear")
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.crearTarea(tarea));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tarea>> listarTareas() {
        return ResponseEntity.ok(tareaService.listarTareas());
    }*/
}

