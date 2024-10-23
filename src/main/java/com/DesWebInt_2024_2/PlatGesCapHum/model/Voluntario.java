package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "voluntarios")
public class Voluntario extends Usuario {

    @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<VoluntarioGrupo> voluntariosGrupo = new HashSet<>();

    public Voluntario() {
        super();
        setTipoUsuario("Voluntario"); // Establece el tipo de usuario como "Voluntario"
    }

    // Getters y setters...

    public Set<VoluntarioGrupo> getVoluntariosGrupo() {
        return voluntariosGrupo;
    }

    public void setVoluntariosGrupo(Set<VoluntarioGrupo> voluntariosGrupo) {
        this.voluntariosGrupo = voluntariosGrupo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Voluntario)) return false;
        Voluntario other = (Voluntario) obj;
        return idUsuario != null && idUsuario.equals(other.idUsuario);
    }
    @Override
    public int hashCode() {
        return 31; // o cualquier otro valor constante, ya que solo necesitas comparar el id
    }
}
