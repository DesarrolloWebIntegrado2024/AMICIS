package com.DesWebInt_2024_2.PlatGesCapHum.repository;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByEstadoTarea(Tarea.EstadoTarea estadoTarea);

    // Consulta personalizada para obtener tareas con un número exacto de voluntarios en el grupo asociado
    @Query("SELECT t FROM Tarea t WHERE SIZE(t.grupo.voluntariosGrupo) = :size")
    List<Tarea> findByGrupoVoluntariosExactSize(@Param("size") int size);

    // Consulta personalizada para obtener tareas con menos de un número determinado de voluntarios en el grupo asociado
    @Query("SELECT t FROM Tarea t WHERE SIZE(t.grupo.voluntariosGrupo) < :size")
    List<Tarea> findByGrupoVoluntariosSizeLessThan(@Param("size") int size);
}