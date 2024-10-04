package com.DesWebInt_2024_2.PlatGesCapHum.service;

import com.DesWebInt_2024_2.PlatGesCapHum.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    /*public Tarea crearTarea(Tarea tarea) {
    }

    public List<Tarea> listarTareas() {
    }*/

    /*public Tarea crearTarea(Tarea tarea) {
        // Lógica adicional para validar fechas, etc.
        return tareaRepository.save(tarea);
    }

    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }*/
}


