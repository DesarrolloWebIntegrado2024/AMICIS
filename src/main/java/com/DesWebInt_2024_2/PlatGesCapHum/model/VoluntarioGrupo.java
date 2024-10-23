package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.*;

@Entity
@Table(name = "voluntarios_grupo")
public class VoluntarioGrupo {

    @EmbeddedId
    private VoluntarioGrupoId id;

    @ManyToOne
    @MapsId("voluntarioId")
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;

    @ManyToOne
    @MapsId("grupoId")
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Column(name = "tipo_voluntario", nullable = false)
    private String tipoVoluntario = "normal"; // Valor por defecto "normal"

    // Getters y setters
    public VoluntarioGrupoId getId() {
        return id;
    }

    public void setId(VoluntarioGrupoId id) {
        this.id = id;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getTipoVoluntario() {
        return tipoVoluntario;
    }

    public void setTipoVoluntario(String tipoVoluntario) {
        this.tipoVoluntario = tipoVoluntario;
    }
}

