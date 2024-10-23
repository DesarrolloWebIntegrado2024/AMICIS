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

    @ManyToOne
    @JoinColumn(name = "tarea_id", nullable = false)
    private Tarea tarea;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
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

        VoluntarioGrupo voluntarioGrupo = new VoluntarioGrupo();
        voluntarioGrupo.setVoluntario(voluntario);
        voluntarioGrupo.setGrupo(this);
        voluntarioGrupo.setTipoVoluntario("normal"); // Por defecto, "normal"
        voluntariosGrupo.add(voluntarioGrupo);
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
        for (VoluntarioGrupo vg : voluntariosGrupo) {
            if (vg.getTipoVoluntario().equals("líder")) {
                return vg.getVoluntario();
            }
        }
        return null; // No hay líder asignado
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
