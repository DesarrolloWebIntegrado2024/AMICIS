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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "voluntarios_grupo",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "voluntario_id")
    )
    private Set<Usuario> voluntarios = new HashSet<>();


    public Grupo() {
    }

    public Grupo(Long id, Tarea tarea, Set<Usuario> voluntarios) {
        this.id = id;
        this.tarea = tarea;
        this.voluntarios = voluntarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Usuario> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(Set<Usuario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
}

