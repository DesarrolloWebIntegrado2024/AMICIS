package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tarea_id", nullable = false)
    private Tarea tarea;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VoluntarioGrupo> voluntariosGrupo = new HashSet<>();

    public Grupo() {
    }

    public Grupo(Long id, Tarea tarea, Set<VoluntarioGrupo> voluntariosGrupo) {
        this.id = id;
        this.tarea = tarea;
        this.voluntariosGrupo = voluntariosGrupo;
    }

    // Método para inscribir voluntarios y establecer tipo_voluntario
    public boolean inscribirVoluntario(Voluntario voluntario) {
        if (voluntariosGrupo.size() >= 10) {
            return false; // No hay más cupo
        }

        // Crear e inicializar el objeto VoluntarioGrupo
        VoluntarioGrupo voluntarioGrupo = new VoluntarioGrupo();
        voluntarioGrupo.setVoluntario(voluntario);
        voluntarioGrupo.setGrupo(this);

        // Inicializar VoluntarioGrupoId con voluntario y grupo
        VoluntarioGrupoId id = new VoluntarioGrupoId(voluntario.getIdUsuario(), this.id);
        voluntarioGrupo.setId(id);

        // Establecer tipo de voluntario por defecto
        voluntarioGrupo.setTipoVoluntario("normal");
        voluntariosGrupo.add(voluntarioGrupo);

        // Actualizar el conteo de voluntarios en la tarea asociada
        if (this.tarea != null) {
            this.tarea.setCantidadVoluntariosInscritos(voluntariosGrupo.size());
        }

        return true;
    }

    public Set<Voluntario> getVoluntarios() {
        Set<Voluntario> voluntarios = new HashSet<>();
        for (VoluntarioGrupo vg : voluntariosGrupo) {
            voluntarios.add(vg.getVoluntario());
        }
        return voluntarios;
    }

    public void asignarLider(Voluntario voluntario) {
        for (VoluntarioGrupo vg : voluntariosGrupo) {
            if (vg.getVoluntario().equals(voluntario)) {
                vg.setTipoVoluntario("líder");
                break; // Solo puede haber un líder, así que rompemos el ciclo
            }
        }
    }

    public void desasignarLider() {
        for (VoluntarioGrupo vg : voluntariosGrupo) {
            if (vg.getTipoVoluntario().equals("líder")) {
                vg.setTipoVoluntario("normal");  // Cambiar al estado de voluntario normal
                break;  // Solo puede haber un líder, así que rompemos el ciclo
            }
        }
    }

    // Método para obtener el líder actual
    public Voluntario obtenerLider() {
        return voluntariosGrupo.stream()
                .filter(vg -> "líder".equals(vg.getTipoVoluntario()))
                .map(VoluntarioGrupo::getVoluntario)
                .findFirst()
                .orElse(null); // Devolver null si no hay líder
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Set<VoluntarioGrupo> getVoluntariosGrupo() {
        return voluntariosGrupo;
    }

    public void setVoluntariosGrupo(Set<VoluntarioGrupo> voluntariosGrupo) {
        this.voluntariosGrupo = voluntariosGrupo;
    }
}
