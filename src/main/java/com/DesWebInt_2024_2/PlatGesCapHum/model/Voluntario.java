package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "voluntarios")
public class Voluntario extends Usuario {
    @ManyToMany(mappedBy = "voluntarios")
    private Set<Grupo> grupos = new HashSet<>();

    public Voluntario() {
        super();
        setTipoUsuario("Voluntario"); // Establece el tipo de usuario como "Voluntario"
    }

    // Getters y setters...

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
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
