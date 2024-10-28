package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoluntarioGrupoId implements Serializable {

    private Long voluntarioId;
    private Long grupoId;

    public VoluntarioGrupoId() {
    }

    public VoluntarioGrupoId(Long voluntarioId, Long grupoId) {
        this.voluntarioId = voluntarioId;
        this.grupoId = grupoId;
    }

    public Long getVoluntarioId() {
        return voluntarioId;
    }

    public void setVoluntarioId(Long voluntarioId) {
        this.voluntarioId = voluntarioId;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoluntarioGrupoId that = (VoluntarioGrupoId) o;
        return Objects.equals(voluntarioId, that.voluntarioId) && Objects.equals(grupoId, that.grupoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voluntarioId, grupoId);
    }
}
