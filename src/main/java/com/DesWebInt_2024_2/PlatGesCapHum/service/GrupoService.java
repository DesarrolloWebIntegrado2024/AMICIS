package com.DesWebInt_2024_2.PlatGesCapHum.service;

import com.DesWebInt_2024_2.PlatGesCapHum.model.*;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.GrupoRepository;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.TareaRepository;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.VoluntarioGrupoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private VoluntarioGrupoRepository voluntarioGrupoRepository;
    @Autowired
    private TareaRepository tareaRepository;
    @PersistenceContext
    private EntityManager entityManager;

    // Método para obtener un grupo por su ID
    public Grupo obtenerGrupoPorId(Long grupoId) {
        return grupoRepository.findById(grupoId).orElse(null);
    }

    @Transactional
    public boolean inscribirVoluntarioAGrupo(Long grupoId, Voluntario voluntario) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));

        // Lógica de inscripción
        boolean inscrito = grupo.inscribirVoluntario(voluntario);

        if (inscrito) {
            grupoRepository.save(grupo);  // Guardar el grupo con el nuevo voluntario
        }

        return inscrito;
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

    @Transactional
    public boolean desinscribirVoluntario(Long voluntarioId, Long grupoId) {
        // 1. Buscar la relación VoluntarioGrupo.
        Optional<VoluntarioGrupo> voluntarioGrupoOpt = voluntarioGrupoRepository.findById(new VoluntarioGrupoId(voluntarioId, grupoId));

        if (voluntarioGrupoOpt.isPresent()) {
            // 2. Eliminar la relación VoluntarioGrupo.
            voluntarioGrupoRepository.delete(voluntarioGrupoOpt.get());

            // 3. Actualizar la cantidad de voluntarios inscritos en la tarea
            Grupo grupo = grupoRepository.findById(grupoId).orElse(null);
            if (grupo != null && grupo.getTarea() != null) {
                int nuevaCantidad = grupo.getTarea().getCantidadVoluntariosInscritos() - 1;
                grupo.getTarea().setCantidadVoluntariosInscritos(Math.max(nuevaCantidad, 0));

                // 4. Guardar cambios en tarea y grupo.
                tareaRepository.save(grupo.getTarea());
                grupoRepository.save(grupo);
            }

            System.out.println("Voluntario desinscrito del grupo y guardado en la base de datos");
            return true;
        } else {
            System.out.println("Relación VoluntarioGrupo no encontrada");
            return false;
        }
    }
}
