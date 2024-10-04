package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lideres")
public class Lider extends Voluntario {

    @OneToMany(mappedBy = "lider")
    private Set<Tarea> tareasAsignadas = new HashSet<>();

    public Lider() {
        super();
    }

    public Lider(Long idUsuario, String nombreUsuario, String emailUsuario, String telefonoUsuario, String contraseniaUsuario, Set<Grupo> grupos, Set<Tarea> tareasAsignadas) {
        super(idUsuario, nombreUsuario, emailUsuario, telefonoUsuario, contraseniaUsuario, grupos);
        this.tareasAsignadas = tareasAsignadas;
    }

    public Set<Tarea> getTareasAsignadas() {
        return tareasAsignadas;
    }

    public void setTareasAsignadas(Set<Tarea> tareasAsignadas) {
        this.tareasAsignadas = tareasAsignadas;
    }
}
