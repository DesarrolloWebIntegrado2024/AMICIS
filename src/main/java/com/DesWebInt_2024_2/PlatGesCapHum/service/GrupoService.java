package com.DesWebInt_2024_2.PlatGesCapHum.service;

import com.DesWebInt_2024_2.PlatGesCapHum.model.*;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.GrupoRepository;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.TareaRepository;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.VoluntarioGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private VoluntarioGrupoRepository voluntarioGrupoRepository;
    @Autowired
    private TareaRepository tareaRepository;

    // Método para obtener un grupo por su ID
    public Grupo obtenerGrupoPorId(Long grupoId) {
        return grupoRepository.findById(grupoId).orElse(null);
    }

    public boolean inscribirVoluntarioAGrupo(Grupo grupo, Voluntario voluntario) {
        if (grupo.getVoluntariosGrupo().size() >= 10) {
            return false; // No hay más cupo
        }

        // Verificar si el voluntario ya está inscrito
        for (VoluntarioGrupo vg : grupo.getVoluntariosGrupo()) {
            if (vg.getVoluntario().equals(voluntario)) {
                return false; // Ya está inscrito
            }
        }

        // Crear nueva relación VoluntarioGrupo
        VoluntarioGrupo voluntarioGrupo = new VoluntarioGrupo();
        voluntarioGrupo.setId(new VoluntarioGrupoId(voluntario.getIdUsuario(), grupo.getId()));
        voluntarioGrupo.setVoluntario(voluntario);
        voluntarioGrupo.setGrupo(grupo);
        voluntarioGrupo.setTipoVoluntario("normal");

        // Agregar a las colecciones
        grupo.getVoluntariosGrupo().add(voluntarioGrupo);
        voluntario.getVoluntariosGrupo().add(voluntarioGrupo);

        // Actualizar la cantidad de voluntarios inscritos en la tarea
        Tarea tarea = grupo.getTarea();
        tarea.setCantidadVoluntariosInscritos(grupo.getVoluntariosGrupo().size());

        grupoRepository.save(grupo);  // Guarda los cambios en el grupo
        tareaRepository.save(tarea);  // Guarda los cambios en la tarea

        return true;
    }

    public void asignarLiderAGrupo(Grupo grupo, Voluntario voluntario) {
        grupo.asignarLider(voluntario);
        grupoRepository.save(grupo);
    }

    public void desasignarLider(Grupo grupo) {
        grupo.desasignarLider();  // Cambiar el tipo_voluntario del líder actual a "normal"
        grupoRepository.save(grupo);  // Guardar los cambios
    }

    public void guardarGrupo(Grupo grupo) {
        grupoRepository.save(grupo);
    }

    public void eliminarGrupo(Long id) {
        Grupo grupo = grupoRepository.findById(id).orElse(null);
        if (grupo != null) {
            // Eliminar las relaciones VoluntarioGrupo asociadas a este grupo
            for (VoluntarioGrupo vg : grupo.getVoluntariosGrupo()) {
                voluntarioGrupoRepository.delete(vg);
            }
            grupoRepository.deleteById(id);
        }
    }
}
