package com.DesWebInt_2024_2.PlatGesCapHum.service;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Tarea;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Voluntario;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
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

    // Método para encontrar una tarea por ID
    public Optional<Tarea> findById(Long id) {
        return tareaRepository.findById(id); // Busca la tarea por ID
    }

    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.findAll(); // Retorna todas las tareas
    }

    // Método para inscribir un voluntario a una tarea
    public boolean inscribirVoluntario(Long tareaId, Voluntario voluntario) {
        Optional<Tarea> tareaOptional = findById(tareaId);

        if (tareaOptional.isPresent()) {
            Tarea tarea = tareaOptional.get();

            // Intenta inscribir al voluntario en la tarea
            boolean inscrito = tarea.inscribirVoluntario(voluntario);

            if (inscrito) {
                // Guarda la tarea actualizada
                tareaRepository.save(tarea);
                return true; // Inscripción exitosa
            }
        }
        return false; // Inscripción fallida
    }
}