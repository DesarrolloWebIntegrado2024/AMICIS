package com.DesWebInt_2024_2.PlatGesCapHum.service;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Tarea;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public void crearTarea(Tarea tarea) {
        // Aquí podrías agregar lógica adicional antes de guardar
        tareaRepository.save(tarea); // Guarda la tarea en la base de datos
    }

    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.findAll(); // Retorna todas las tareas
    }
}



