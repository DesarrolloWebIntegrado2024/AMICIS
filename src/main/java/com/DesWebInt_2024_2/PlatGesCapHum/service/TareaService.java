package com.DesWebInt_2024_2.PlatGesCapHum.service;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Grupo;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Tarea;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Voluntario;
import com.DesWebInt_2024_2.PlatGesCapHum.model.VoluntarioGrupo;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.GrupoRepository;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.TareaRepository;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.VoluntarioGrupoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private VoluntarioGrupoRepository voluntarioGrupoRepository;
    @Autowired
    private GrupoRepository grupoRepository;

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

    public void actualizarTarea(Tarea tarea) {
        System.out.println("Actualizando tarea: " + tarea);
        tareaRepository.save(tarea);
        System.out.println("Tarea actualizada con éxito: " + tarea);
    }

    // Método para obtener una tarea por ID
    public Tarea obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id).orElse(null);
    }

    // Método para eliminar una tarea
    @Transactional
    public void eliminarTarea(Long id) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);

        if (tarea != null) {
            if (tarea.getEstadoTarea() == Tarea.EstadoTarea.PENDIENTE) {
                Grupo grupo = tarea.getGrupo();
                if (grupo != null) {
                    // Primero, elimina los VoluntarioGrupo asociados al grupo
                    voluntarioGrupoRepository.deleteAll(grupo.getVoluntariosGrupo());

                    // Luego, elimina el grupo
                    grupoRepository.delete(grupo);
                }
                // Finalmente, elimina la tarea
                tareaRepository.delete(tarea);
            } else {
                throw new IllegalStateException("La tarea no se puede eliminar porque no está en estado PENDIENTE.");
            }
        } else {
            throw new EntityNotFoundException("Tarea no encontrada con el ID: " + id);
        }
    }

}